package com.hardews.jizhang.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserVo {

    @NotBlank
    @Size(min = 6,max = 12)
    private String username;

    @NotBlank
    @Size(min = 6,max = 12)
    private String password;

    @NotBlank
    private String email;
}
