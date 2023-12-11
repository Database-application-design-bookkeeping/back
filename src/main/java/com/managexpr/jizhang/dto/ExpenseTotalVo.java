package com.managexpr.jizhang.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExpenseTotalVo {
    private Long total;
    private List<ExpenseVo> expenseVos;
}
