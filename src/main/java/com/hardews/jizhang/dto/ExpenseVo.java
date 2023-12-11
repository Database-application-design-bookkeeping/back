package com.hardews.jizhang.dto;


import lombok.Data;

import java.util.Date;

@Data
public class ExpenseVo {
//    private String username;
    private String categoryName;
    private Long amount;
    private String remark;
    private Date createTime;
    private String output_method;

}
