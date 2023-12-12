package com.hardews.jizhang.controller;


import com.hardews.jizhang.dto.IncomeDto;
import com.hardews.jizhang.dto.IncomeTotalVo;
import com.hardews.jizhang.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hardews.jizhang.service.IncomeService;

@RestController
@RequestMapping("jizhang/income")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @GetMapping("/day")
    public R day(){
        IncomeTotalVo incomeTotalVos = incomeService.getIncomeByDay();
        return R.ok("查询成功").put("data", incomeTotalVos);
    }

    @GetMapping("/week")
    public R week(){
        IncomeTotalVo incomeTotalVos = incomeService.getIncomeByWeek();
        return R.ok("查询成功").put("data", incomeTotalVos);
    }

    @GetMapping("/month")
    public R month(){
        IncomeTotalVo incomeTotalVos = incomeService.getIncomeByMonth();
        return R.ok("查询成功").put("data", incomeTotalVos);
    }

    @GetMapping("/total")
    public R total(){
        IncomeTotalVo incomeTotalVos = incomeService.getIncomeTotal();
        return R.ok("查询成功").put("data", incomeTotalVos);
    }

    @RequestMapping("/save")
    public R save(@RequestBody IncomeDto incomeDto){
		incomeService.saveIncome(incomeDto);
        return R.ok("保存成功");
    }
}
