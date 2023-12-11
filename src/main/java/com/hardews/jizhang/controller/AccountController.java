package com.hardews.jizhang.controller;

import com.hardews.jizhang.dto.AccountVo;
import com.hardews.jizhang.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hardews.jizhang.service.AccountService;

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
