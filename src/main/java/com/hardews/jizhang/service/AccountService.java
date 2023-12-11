package com.hardews.jizhang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hardews.jizhang.dto.AccountVo;
import com.hardews.jizhang.entity.AccountEntity;
import com.hardews.jizhang.utils.PageUtils;

import java.util.Map;

public interface AccountService extends IService<AccountEntity> {

    PageUtils queryPage(Map<String, Object> params);

    AccountVo getInfo();
}

