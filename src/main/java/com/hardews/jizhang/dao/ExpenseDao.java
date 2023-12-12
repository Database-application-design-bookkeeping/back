package com.hardews.jizhang.dao;

import com.hardews.jizhang.entity.ExpenseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpenseDao extends BaseMapper<ExpenseEntity> {

    List<ExpenseEntity> selectExpenseByDay(@Param("userId") Long userId);

    List<ExpenseEntity> selectExpenseByWeek(@Param("userId") Long userId);

    List<ExpenseEntity> selectExpenseByMonth(@Param("userId") Long userId);

    List<ExpenseEntity> selectExpenseTotal(@Param("userId") Long userId);
}
