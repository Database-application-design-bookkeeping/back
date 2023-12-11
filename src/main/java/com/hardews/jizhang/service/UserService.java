package com.hardews.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hardews.jizhang.dto.LoginVo;
import com.hardews.jizhang.dto.LoginVoByEmail;
import com.hardews.jizhang.dto.UserVo;
import com.hardews.jizhang.entity.UserEntity;
import com.hardews.jizhang.utils.PageUtils;
import com.hardews.jizhang.utils.R;

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

