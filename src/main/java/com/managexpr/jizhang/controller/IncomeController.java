package com.managexpr.jizhang.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.managexpr.jizhang.dto.IncomeDto;
import com.managexpr.jizhang.dto.IncomeTotalVo;
import com.managexpr.jizhang.utils.PageUtils;
import com.managexpr.jizhang.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.managexpr.jizhang.entity.IncomeEntity;
import com.managexpr.jizhang.service.IncomeService;

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
