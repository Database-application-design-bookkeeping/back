package com.hardews.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hardews.jizhang.dto.ExceptionVo;
import com.hardews.jizhang.entity.ExceptionEntity;
import com.hardews.jizhang.utils.PageUtils;

import java.util.Map;

public interface ExceptionService extends IService<ExceptionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(Long amount);

    void updateException(Long amount);

    ExceptionVo getInfo();
}

