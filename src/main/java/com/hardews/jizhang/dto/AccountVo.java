package com.hardews.jizhang.dto;

import lombok.Data;

@Data
public class AccountVo {
    AccountBalanceVo balance;
    AccountIncomeVo income;
    AccountExpenseVo expense;
}


