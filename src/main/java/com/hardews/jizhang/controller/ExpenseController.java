package com.hardews.jizhang.controller;

import com.hardews.jizhang.dto.ExpenseDto;
import com.hardews.jizhang.dto.ExpenseTotalVo;
import com.hardews.jizhang.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hardews.jizhang.service.ExpenseService;

@RestController
@RequestMapping("jizhang/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/day")
    public R day(){
		ExpenseTotalVo expense = expenseService.getExpenseByDay();
        return R.ok("查询成功").put("data", expense);
    }

    @GetMapping("/week")
    public R week(){
        ExpenseTotalVo expense = expenseService.getExpenseByWeek();
        return R.ok("查询成功").put("data", expense);
    }

    @GetMapping("/month")
    public R month(){
        ExpenseTotalVo expense = expenseService.getExpenseByMonth();
        return R.ok("查询成功").put("data", expense);
    }

    @GetMapping("/total")
    public R total(){
        ExpenseTotalVo expense = expenseService.getExpenseTotal();
        return R.ok("查询成功").put("data", expense);
    }
    @RequestMapping("/save")
    public R save(@RequestBody ExpenseDto expenseDto){
		expenseService.saveExp(expenseDto);
        return R.ok("保存成功");
    }
}
