package com.hardews.jizhang.dao;

import com.hardews.jizhang.entity.IncomeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IncomeDao extends BaseMapper<IncomeEntity> {

    List<IncomeEntity> selectIncomeByDay(@Param("accountId") Long id);

    List<IncomeEntity> selectIncomeByMonth(@Param("accountId") Long id);

    List<IncomeEntity> selectIncomeByWeek(@Param("accountId") Long id);

}
