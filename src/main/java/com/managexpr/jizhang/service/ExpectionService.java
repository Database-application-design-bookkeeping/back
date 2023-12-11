package com.managexpr.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.managexpr.jizhang.dto.ExpectionVo;
import com.managexpr.jizhang.entity.ExpectionEntity;
import com.managexpr.jizhang.utils.PageUtils;

import java.util.Map;

public interface ExpectionService extends IService<ExpectionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(Long amount);

    void updateExpection(Long amount);

    ExpectionVo getInfo();
}

