package com.hardews.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hardews.jizhang.dto.IncomeDto;
import com.hardews.jizhang.dto.IncomeTotalVo;
import com.hardews.jizhang.entity.IncomeEntity;
import com.hardews.jizhang.utils.PageUtils;

import java.util.Map;

public interface IncomeService extends IService<IncomeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveIncome(IncomeDto incomeDto);

    IncomeTotalVo getIncomeByDay();

    IncomeTotalVo getIncomeByWeek();

    IncomeTotalVo getIncomeByMonth();

    IncomeTotalVo getIncomeTotal();
}

