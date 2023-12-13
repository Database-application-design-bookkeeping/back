package com.hardews.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hardews.jizhang.dto.LoginVo;
import com.hardews.jizhang.dto.LoginVoByEmail;
import com.hardews.jizhang.dto.UserVo;
import com.hardews.jizhang.entity.UserEntity;
import com.hardews.jizhang.utils.PageUtils;
import com.hardews.jizhang.utils.R;

import javax.mail.MessagingException;
import java.util.Map;

public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R register(UserVo user);

    R login(LoginVo loginVo);

    R loginByEmail(LoginVoByEmail loginVoByEmail);

    R sendCode(String email) throws MessagingException;

    R updateUser(UserVo user);
}

