package com.hardews.jizhang.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName("income")
public class IncomeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;

	private Long accountId;

	private Long userId;

	private Integer categoryId;

	private Long amount;

	private String remark;

	private Date createTime;

	private Date updateTime;

	private String inputMethod;

}
