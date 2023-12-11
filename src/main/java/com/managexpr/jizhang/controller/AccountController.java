package com.managexpr.jizhang.controller;

import java.util.Arrays;
import java.util.Map;

import com.managexpr.jizhang.dto.AccountVo;
import com.managexpr.jizhang.utils.PageUtils;
import com.managexpr.jizhang.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.managexpr.jizhang.entity.AccountEntity;
import com.managexpr.jizhang.service.AccountService;

@RestController
@RequestMapping("jizhang/account")
@Api("显示目前的余额")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/info")
    public R info(){
        AccountVo  accountVo = accountService.getInfo();
        return R.ok("查询成功").put("data", accountVo);
    }

}
