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

    @GetMapping("/info/day")
    public R infoByDay(){
        IncomeTotalVo incomeTotalVos = incomeService.getIncomeByDay();
        return R.ok("查询成功").put("data", incomeTotalVos);
    }

    @GetMapping("/info/week")
    public R infoByWeek(){
        IncomeTotalVo incomeTotalVos = incomeService.getIncomeByWeek();
        return R.ok("查询成功").put("data", incomeTotalVos);
    }

    @GetMapping("/info/month")
    public R infoByMonth(){
        IncomeTotalVo incomeTotalVos = incomeService.getIncomeByMonth();
        return R.ok("查询成功").put("data", incomeTotalVos);
    }

    @RequestMapping("/save")
    public R save(@RequestBody IncomeDto incomeDto){
		incomeService.saveIncome(incomeDto);
        return R.ok("保存成功");
    }
}
