package com.managexpr.jizhang.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class ExpenseDto {

    @NotEmpty
    private Long amount;

    @NotEmpty
    private Integer categoryId;
    /**
     *
     */
    private String remark;
    /**
     *
     */
    private String output_method;
}
