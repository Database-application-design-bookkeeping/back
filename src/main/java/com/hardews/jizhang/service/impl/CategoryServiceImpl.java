package com.hardews.jizhang.service.impl;

import com.hardews.jizhang.dao.CategoryDao;
import com.hardews.jizhang.utils.PageUtils;
import com.hardews.jizhang.utils.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.hardews.jizhang.entity.CategoryEntity;
import com.hardews.jizhang.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public ArrayList Outcome() {
        return this.baseMapper.selectOutCome();
    }

    @Override
    public ArrayList Income() {
        return this.baseMapper.selectIncome();
    }

}