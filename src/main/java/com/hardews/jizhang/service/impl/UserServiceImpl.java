package com.hardews.jizhang.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.hardews.jizhang.dao.UserDao;
import com.hardews.jizhang.dto.*;
import com.hardews.jizhang.entity.AccountEntity;
import com.hardews.jizhang.service.AccountService;
import com.hardews.jizhang.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.hardews.jizhang.entity.UserEntity;
import com.hardews.jizhang.service.UserService;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {


    @Autowired
    private StringRedisTemplate redis;

    @Autowired
    private AccountService accountService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R register(UserVo userVo) {

        if (ObjectUtils.isEmpty(userVo)){
            return R.ok("参数不能为空");
        }

        if (StringUtils.isEmpty(userVo.getUsername())){
            return R.ok("用户名不能为空");
        }
        if (StringUtils.isEmpty(userVo.getPassword())){
            return R.ok("密码不能为空");
        }
        if (StringUtils.isEmpty(userVo.getEmail())){
            return R.ok("不能为空");
        }

        //通过用户名来判断是否是唯一的
        int count = this.count(new QueryWrapper<UserEntity>().eq("username", userVo.getUsername()));
        if (count > 0){
            return R.ok("用户名已存在");
        }

        int count2 = this.count(new QueryWrapper<UserEntity>().eq("email", userVo.getEmail()));
        if (count2 > 0){
            return R.ok("邮箱已被绑定");
        }

        String encode_pwd = DigestUtils.md5DigestAsHex(userVo.getPassword().getBytes(StandardCharsets.UTF_8));
        UserEntity user = new UserEntity();
        user.setUsername(userVo.getUsername());
        user.setPassword(encode_pwd);
        user.setEmail(userVo.getEmail());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        this.save(user);

        UserEntity u = this.getOne(new QueryWrapper<UserEntity>().eq("username", userVo.getUsername()));
        if (ObjectUtils.isEmpty(u)){
            return R.error(500, "系统错误");
        }
        AccountEntity account = new AccountEntity();
        account.setUserId(Math.toIntExact(u.getId()));
        account.setBalance(0L);
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());

        accountService.save(account);

        return R.ok("注册成功");
    }

    @Override
    public R login(LoginVo loginVo) {
        //判空
        if(StringUtils.isEmpty(loginVo.getUsername())){
            return R.ok("用户名不能为空");
        }
        if (StringUtils.isEmpty(loginVo.getPassword())){
            return R.ok("密码不能为空");
        }

        UserEntity user = this.getOne(new QueryWrapper<UserEntity>().eq("username", loginVo.getUsername()));

        if (DigestUtils.md5DigestAsHex(loginVo.getPassword().getBytes(StandardCharsets.UTF_8)).equals(user.getPassword())){
            return generateToken(user);
        }

        return R.ok("用户名或密码错误");
    }

    @Override
    public R loginByEmail(LoginVoByEmail loginVoByEmail) {
        //判空
        if(StringUtils.isEmpty(loginVoByEmail.getEmail())){
            return R.error(400,"邮箱不能为空");
        }
        if (StringUtils.isEmpty(loginVoByEmail.getCode())){
            return R.error(400,"验证码不能为空");
        }

        UserEntity user = this.getOne(new QueryWrapper<UserEntity>().eq("email", loginVoByEmail.getEmail()));
        if (ObjectUtils.isEmpty(user)){
            return R.error(400,"不存在此用户");
        }

        //从redis获取验证码
        String code_redis = redis.opsForValue().get(loginVoByEmail.getEmail());
        if (code_redis == null){
            return R.error(400,"验证码失效");
        }
        if(!loginVoByEmail.getCode().equals(code_redis)){
            return R.error(400,"验证码错误");
        }

        redis.delete(loginVoByEmail.getEmail());

        return generateToken(user);
    }

    @Override
    public R sendCode(String email) throws MessagingException {
        UserEntity user = this.getOne(new QueryWrapper<UserEntity>().eq("email", email));
        if (ObjectUtils.isEmpty(user)){
            return R.ok("不存在此用户");
        }

        int code = (int) ((Math.random() * 9 + 1) * 100000);
        MailDto mailDto = new MailDto();
        mailDto.setRecipient(email);
        mailDto.setSubject("邮箱验证码");
        mailDto.setContent("验证码为:"+code+",2分钟后失效");

        //放入redis
        redis.opsForValue().set(email, String.valueOf(code),2, TimeUnit.MINUTES);

        return SendQQMailUtil.sendEmail(mailDto);
    }

    @Override
    public R updateUser(UserVo userVo) {
        if (ObjectUtils.isEmpty(userVo)){
            return R.error(403,"不合法的操作");
        }
        // 获取当前用户id
        Map<String, Claim> payload = JwtPayloadHolder.getClaims();
        Long id = Long.valueOf(payload.get("id").asString());
        String username = payload.get("username").asString();

        UserEntity userEntity = this.getOne(new QueryWrapper<UserEntity>().eq("username", username));
        if (ObjectUtils.isEmpty(userEntity)){
            return R.error(200, "无此用户");
        }

        // 用户名不能被修改
        if (!StringUtils.isEmpty(userVo.getPassword())){
            userEntity.setPassword(DigestUtils.md5DigestAsHex(userVo.getPassword().getBytes()));
        }
        if (!StringUtils.isEmpty(userVo.getEmail())){
            userEntity.setEmail(userVo.getEmail());
        }
        userEntity.setUpdateTime(new Date());
        userEntity.setId(id);

        // debug
        System.out.print("update info: ");
        System.out.println(userEntity);

        this.updateById(userEntity);
        return R.ok("修改成功");
    }

    private R generateToken(@org.jetbrains.annotations.NotNull UserEntity user) {
        Map<String, String> payload = new HashMap<>();
        payload.put("username", user.getUsername());
        payload.put("id", user.getId().toString());
        payload.put("email", user.getEmail());

        String token = Jwt.getToken(payload);
        return R.ok("登录成功").put("data", token);
    }
}