package com.hardews.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.hardews.jizhang.entity.CategoryEntity;
import com.hardews.jizhang.utils.PageUtils;

import java.util.ArrayList;
import java.util.Map;

public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ArrayList Outcome();

    ArrayList Income();
}

