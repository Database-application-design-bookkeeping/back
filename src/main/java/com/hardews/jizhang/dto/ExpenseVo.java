package com.hardews.jizhang.dto;


import lombok.Data;

import java.util.Date;

@Data
public class ExpenseVo {
    private String categoryName;
    private Long amount;
    private String remark;
    private Date createTime;
    private String outputMethod;
}
