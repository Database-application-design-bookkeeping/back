package com.managexpr.jizhang.dao;

import com.managexpr.jizhang.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

     ArrayList<CategoryEntity> selectOutCome();

     ArrayList selectIncome();
}
