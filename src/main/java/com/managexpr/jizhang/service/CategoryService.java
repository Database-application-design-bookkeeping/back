package com.managexpr.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.managexpr.jizhang.entity.CategoryEntity;
import com.managexpr.jizhang.utils.PageUtils;

import java.util.ArrayList;
import java.util.Map;

public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ArrayList Outcome();

    ArrayList Income();
}

