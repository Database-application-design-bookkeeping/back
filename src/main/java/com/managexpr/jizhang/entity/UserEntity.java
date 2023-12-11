package com.managexpr.jizhang.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@TableName("user")
@ApiModel
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;

	@NotEmpty
	@Size(min = 6,max = 12)
	@ApiModelProperty("用户名")
	private String username;

	@NotEmpty
	@Size(min = 6,max = 12)
	@ApiModelProperty("用户密码")
	private String password;

	@NotEmpty
	@ApiModelProperty("用户邮箱")

	private String email;

	private Date createTime;

	private Date updateTime;

}
