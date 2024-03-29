package com.hardews.jizhang.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class IncomeDto {
    @NotEmpty
    private Integer categoryId;

    @NotEmpty
    private Long amount;

    private String remark;

    private String inputMethod;
}
