package com.managexpr.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.managexpr.jizhang.dto.ExpenseTotalVo;
import com.managexpr.jizhang.dto.ExpenseVo;
import com.managexpr.jizhang.dto.ExpenseDto;
import com.managexpr.jizhang.entity.ExpenseEntity;
import com.managexpr.jizhang.utils.PageUtils;

import java.util.Map;

public interface ExpenseService extends IService<ExpenseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ExpenseVo getExpense();

    void saveExp(ExpenseDto expenseDto);

    ExpenseTotalVo getExpenseByDay();

    ExpenseTotalVo getExpenseByWeek();

    ExpenseTotalVo getExpenseByMonth();
}

