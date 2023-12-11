package com.hardews.jizhang.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName("expectation")
public class ExpectationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;

	private Long userId;

	private Long amount;
}
