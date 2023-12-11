package com.managexpr.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.managexpr.jizhang.dto.LoginVo;
import com.managexpr.jizhang.dto.LoginVoByEmail;
import com.managexpr.jizhang.dto.UserVo;
import com.managexpr.jizhang.entity.UserEntity;
import com.managexpr.jizhang.utils.PageUtils;
import com.managexpr.jizhang.utils.R;

import java.util.Map;

public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R register(UserVo user);

    R login(LoginVo loginVo);

    R loginByEmail(LoginVoByEmail loginVoByEmail);

    R sendCode(String email);

    R updateUser(UserVo user);

    void logout();
}

