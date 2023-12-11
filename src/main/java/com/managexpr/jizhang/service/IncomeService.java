package com.managexpr.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.managexpr.jizhang.dto.IncomeDto;
import com.managexpr.jizhang.dto.IncomeTotalVo;
import com.managexpr.jizhang.entity.IncomeEntity;
import com.managexpr.jizhang.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface IncomeService extends IService<IncomeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveIncome(IncomeDto incomeDto);

    IncomeTotalVo getIncomeByDay();

    IncomeTotalVo getIncomeByWeek();

    IncomeTotalVo getIncomeByMonth();
}

