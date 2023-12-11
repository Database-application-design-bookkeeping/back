package com.managexpr.jizhang.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.managexpr.jizhang.dto.ExpectionVo;
import com.managexpr.jizhang.utils.PageUtils;
import com.managexpr.jizhang.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.managexpr.jizhang.dao.ExpectionDao;
import com.managexpr.jizhang.entity.ExpectionEntity;
import com.managexpr.jizhang.service.ExpectionService;


@Service("expectionService")
public class ExpectionServiceImpl extends ServiceImpl<ExpectionDao, ExpectionEntity> implements ExpectionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExpectionEntity> page = this.page(
                new Query<ExpectionEntity>().getPage(params),
                new QueryWrapper<ExpectionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void save(Long amount) {
        ExpectionEntity expectionEntity = new ExpectionEntity();
        expectionEntity.setUserId(Long.valueOf(StpUtil.getLoginId().toString()));
        expectionEntity.setAmount(amount);
        this.save(expectionEntity);
    }

    @Override
    public void updateExpection(Long amount) {
        Long id = Long.valueOf(StpUtil.getLoginId().toString());
        ExpectionEntity expectionEntity = this.getOne(new QueryWrapper<ExpectionEntity>().eq("user_id", id));
        expectionEntity.setAmount(amount);
        this.updateById(expectionEntity);
    }

    @Override
    public ExpectionVo getInfo() {
        Long id = Long.valueOf(StpUtil.getLoginId().toString());
        ExpectionEntity expectionEntity = this.getOne(new QueryWrapper<ExpectionEntity>().eq("user_id", id));
        ExpectionVo expectionVo = new ExpectionVo();
        expectionVo.setAmount(expectionEntity.getAmount());
        return expectionVo;
    }

}