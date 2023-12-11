package com.managexpr.jizhang.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author ly
 * @email 3194867951@qq.com
 * @date 2023-05-13 19:14:41
 */
@Data
@TableName("expection")
public class ExpectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;

	private Long userId;

	private Long amount;
}
