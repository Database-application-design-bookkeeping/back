package com.hardews.jizhang.service.impl;



import com.hardews.jizhang.dao.AccountDao;
import com.hardews.jizhang.dto.AccountVo;
import com.hardews.jizhang.entity.UserEntity;
import com.hardews.jizhang.service.UserService;
import com.hardews.jizhang.utils.JwtPayloadHolder;
import com.hardews.jizhang.utils.PageUtils;
import com.hardews.jizhang.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.hardews.jizhang.entity.AccountEntity;
import com.hardews.jizhang.service.AccountService;


@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountDao, AccountEntity> implements AccountService {

    @Autowired
    private UserService userService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccountEntity> page = this.page(
                new Query<AccountEntity>().getPage(params),
                new QueryWrapper<AccountEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public AccountVo getInfo() {
        //获取当前登录用户id
        Long id = JwtPayloadHolder.getClaims();
        UserEntity user = userService.getById(id);
        AccountVo accountVo = new AccountVo();
        accountVo.setUsername(user.getUsername());

        //获取余额
        AccountEntity account = this.getOne(new QueryWrapper<AccountEntity>().eq("user_id", id));
        accountVo.setBalance(account.getBalance());

        JwtPayloadHolder.clear();
        return accountVo;

    }

}