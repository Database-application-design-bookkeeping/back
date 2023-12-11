package com.managexpr.jizhang.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.managexpr.jizhang.component.MailUtils;
import com.managexpr.jizhang.dao.ExpenseDao;
import com.managexpr.jizhang.dto.ExpenseTotalVo;
import com.managexpr.jizhang.dto.MailDto;
import com.managexpr.jizhang.entity.ExpectionEntity;
import com.managexpr.jizhang.entity.ExpenseEntity;
import com.managexpr.jizhang.entity.UserEntity;
import com.managexpr.jizhang.service.ExpectionService;
import com.managexpr.jizhang.service.ExpenseService;
import com.managexpr.jizhang.service.UserService;
import com.managexpr.jizhang.service.impl.ExpenseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private MailUtils mailUtils;


    // 测试登录  ---- http://localhost:8081/acc/doLogin?name=zhang&pwd=123456
    @RequestMapping("doLogin")
    public SaResult doLogin(String name, String pwd) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }

    // 查询登录状态  ---- http://localhost:8081/acc/isLogin
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        MailDto mailBean = new MailDto();
        mailBean.setRecipient("676376165@qq.com");
        mailBean.setSubject("SpringBootMail之这是一封文本的邮件");
        mailBean.setContent("SpringBootMail发送一个简单格式的邮件，时间为：");

        mailUtils.sendSimpleMail(mailBean);
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @RequestMapping("logout")
    public void logout() {
//        StpUtil.logout();
//        return SaResult.ok();

    }
}
