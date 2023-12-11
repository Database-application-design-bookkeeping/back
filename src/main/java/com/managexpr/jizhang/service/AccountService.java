package com.managexpr.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.managexpr.jizhang.dto.AccountVo;
import com.managexpr.jizhang.entity.AccountEntity;
import com.managexpr.jizhang.utils.PageUtils;

import java.util.Map;

public interface AccountService extends IService<AccountEntity> {

    PageUtils queryPage(Map<String, Object> params);

    AccountVo getInfo();
}

