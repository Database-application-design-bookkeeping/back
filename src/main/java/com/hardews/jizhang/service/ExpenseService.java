package com.hardews.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hardews.jizhang.dto.ExpenseDto;
import com.hardews.jizhang.dto.ExpenseTotalVo;
import com.hardews.jizhang.dto.ExpenseVo;
import com.hardews.jizhang.entity.ExpenseEntity;
import com.hardews.jizhang.utils.PageUtils;

import java.util.Map;

public interface ExpenseService extends IService<ExpenseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ExpenseVo getExpense();

    void saveExp(ExpenseDto expenseDto);

    ExpenseTotalVo getExpenseByDay();

    ExpenseTotalVo getExpenseByWeek();

    ExpenseTotalVo getExpenseByMonth();

    ExpenseTotalVo getExpenseTotal();
}

