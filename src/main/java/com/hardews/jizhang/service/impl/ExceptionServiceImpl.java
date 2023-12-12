package com.hardews.jizhang.service.impl;

import com.hardews.jizhang.dao.ExceptionDao;
import com.hardews.jizhang.dto.ExceptionVo;
import com.hardews.jizhang.utils.JwtPayloadHolder;
import com.hardews.jizhang.utils.PageUtils;
import com.hardews.jizhang.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.hardews.jizhang.entity.ExceptionEntity;
import com.hardews.jizhang.service.ExceptionService;


@Service("exceptionService")
public class ExceptionServiceImpl extends ServiceImpl<ExceptionDao, ExceptionEntity> implements ExceptionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExceptionEntity> page = this.page(
                new Query<ExceptionEntity>().getPage(params),
                new QueryWrapper<ExceptionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void save(Long amount) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setUserId(Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString()));
        exceptionEntity.setAmount(amount);
        this.save(exceptionEntity);
    }

    @Override
    public void updateException(Long amount) {
        Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());
        ExceptionEntity exceptionEntity = this.getOne(new QueryWrapper<ExceptionEntity>().eq("user_id", id));
        exceptionEntity.setAmount(amount);
        this.updateById(exceptionEntity);
    }

    @Override
    public ExceptionVo getInfo() {
        Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());
        ExceptionEntity exceptionEntity = this.getOne(new QueryWrapper<ExceptionEntity>().eq("user_id", id));
        ExceptionVo exceptionVo = new ExceptionVo();
        exceptionVo.setAmount(exceptionEntity.getAmount());
        return exceptionVo;
    }

}