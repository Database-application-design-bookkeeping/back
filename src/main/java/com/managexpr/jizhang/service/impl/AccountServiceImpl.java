package com.managexpr.jizhang.service.impl;



import cn.dev33.satoken.stp.StpUtil;
import com.managexpr.jizhang.dto.AccountVo;
import com.managexpr.jizhang.dto.UserVo;
import com.managexpr.jizhang.entity.UserEntity;
import com.managexpr.jizhang.service.UserService;
import com.managexpr.jizhang.utils.PageUtils;
import com.managexpr.jizhang.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.managexpr.jizhang.dao.AccountDao;
import com.managexpr.jizhang.entity.AccountEntity;
import com.managexpr.jizhang.service.AccountService;


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
        Long id = Long.valueOf(StpUtil.getLoginId().toString());
        UserEntity user = userService.getById(id);
        AccountVo accountVo = new AccountVo();
        accountVo.setUsername(user.getUsername());

        //获取余额
        AccountEntity account = this.getOne(new QueryWrapper<AccountEntity>().eq("user_id", id));
        accountVo.setBalance(account.getBalance());

        return accountVo;

    }

}