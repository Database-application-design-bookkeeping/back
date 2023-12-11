package com.hardews.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hardews.jizhang.dto.ExpectationVo;
import com.hardews.jizhang.entity.ExpectationEntity;
import com.hardews.jizhang.utils.PageUtils;

import java.util.Map;

public interface ExpectationService extends IService<ExpectationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(Long amount);

    void updateExpectation(Long amount);

    ExpectationVo getInfo();
}

