package com.managexpr.jizhang.dto;


import lombok.Data;

import java.util.Date;

@Data
public class IncomeVo {

    private String categoryName;

    private Long amount;
    /**
     *
     */
    private String remark;

    private Date createTime;

    private String input_method;
}
