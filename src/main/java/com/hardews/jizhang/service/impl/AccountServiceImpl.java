package com.hardews.jizhang.service.impl;



import com.hardews.jizhang.dao.AccountDao;
import com.hardews.jizhang.dto.*;
import com.hardews.jizhang.entity.IncomeEntity;
import com.hardews.jizhang.entity.UserEntity;
import com.hardews.jizhang.service.ExpenseService;
import com.hardews.jizhang.service.IncomeService;
import com.hardews.jizhang.service.UserService;
import com.hardews.jizhang.utils.JwtPayloadHolder;
import com.hardews.jizhang.utils.PageUtils;
import com.hardews.jizhang.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.hardews.jizhang.entity.AccountEntity;
import com.hardews.jizhang.service.AccountService;


@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountDao, AccountEntity> implements AccountService {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private ExpenseService expenseService;

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
        Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());
        AccountVo accountVo = new AccountVo();

        // 获取今日收入
        Long incomeTodayTotal = incomeService.getIncomeByDay().getTotal();
        // 获取今日支出
        Long expenseTodayTotal = expenseService.getExpenseByDay().getTotal();
        // 今日收益
        Long todayProfit = incomeTodayTotal - expenseTodayTotal;

        // 获取本周收入
        Long incomeWeekTotal = incomeService.getIncomeByDay().getTotal();
        // 获取本周支出
        Long expenseWeekTotal = expenseService.getExpenseByDay().getTotal();
        // 本周收益
        Long weekProfit = incomeWeekTotal - expenseWeekTotal;

        // 获取本月收入
        Long incomeMonthTotal = incomeService.getIncomeByDay().getTotal();
        // 获取本月支出
        Long expenseMonthTotal = expenseService.getExpenseByDay().getTotal();
        // 本月收益
        Long monthProfit = incomeMonthTotal - expenseMonthTotal;

        // 获取本月收入
        Long incomeTotal = incomeService.getIncomeTotal().getTotal();
        // 获取本月支出
        Long expenseTotal = expenseService.getExpenseTotal().getTotal();
        // 本月收益
        Long ProfitTotal = incomeTotal - expenseTotal;

        AccountIncomeVo aiv = new AccountIncomeVo();
        aiv.setDay(incomeTodayTotal);
        aiv.setWeek(incomeWeekTotal);
        aiv.setMonth(incomeMonthTotal);
        aiv.setTotal(incomeTotal);

        AccountExpenseVo aev = new AccountExpenseVo();
        aev.setDay(expenseTodayTotal);
        aev.setWeek(expenseWeekTotal);
        aev.setMonth(expenseMonthTotal);
        aev.setTotal(expenseTotal);

        AccountBalanceVo abv = new AccountBalanceVo();
        abv.setDay(todayProfit);
        abv.setWeek(weekProfit);
        abv.setMonth(monthProfit);
        abv.setTotal(ProfitTotal);

        accountVo.setAccountIncomeVo(aiv);
        accountVo.setAccountExpenseVo(aev);
        accountVo.setAccountBalanceVo(abv);

        JwtPayloadHolder.clear();
        return accountVo;

    }

}