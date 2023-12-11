package com.managexpr.jizhang.dao;

import com.managexpr.jizhang.entity.ExpenseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpenseDao extends BaseMapper<ExpenseEntity> {

    List<ExpenseEntity> selectExpenseByDay(@Param("userId") Long userId);

    List<ExpenseEntity> selectExpenseByWeek(@Param("userId") Long userId);

    List<ExpenseEntity> selectExpenseByMonth(@Param("userId") Long userId);

}
