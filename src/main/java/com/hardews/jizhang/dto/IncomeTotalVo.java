package com.hardews.jizhang.dto;

import lombok.Data;

import java.util.List;

@Data
public class IncomeTotalVo {
    //总金额
    private Long total;
    private List<IncomeVo> incomeVos;
}
