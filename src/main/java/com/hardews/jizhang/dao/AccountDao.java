package com.hardews.jizhang.dao;

import com.hardews.jizhang.entity.AccountEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDao extends BaseMapper<AccountEntity> {
	
}
