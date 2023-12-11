package com.hardews.jizhang.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.hardews.jizhang.dao.ExpectationDao;
import com.hardews.jizhang.dto.ExpectationVo;
import com.hardews.jizhang.utils.PageUtils;
import com.hardews.jizhang.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.hardews.jizhang.entity.ExpectationEntity;
import com.hardews.jizhang.service.ExpectationService;


@Service("expectationService")
public class ExpectationServiceImpl extends ServiceImpl<ExpectationDao, ExpectationEntity> implements ExpectationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExpectationEntity> page = this.page(
                new Query<ExpectationEntity>().getPage(params),
                new QueryWrapper<ExpectationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void save(Long amount) {
        ExpectationEntity expectationEntity = new ExpectationEntity();
        expectationEntity.setUserId(Long.valueOf(StpUtil.getLoginId().toString()));
        expectationEntity.setAmount(amount);
        this.save(expectationEntity);
    }

    @Override
    public void updateExpectation(Long amount) {
        Long id = Long.valueOf(StpUtil.getLoginId().toString());
        ExpectationEntity expectationEntity = this.getOne(new QueryWrapper<ExpectationEntity>().eq("user_id", id));
        expectationEntity.setAmount(amount);
        this.updateById(expectationEntity);
    }

    @Override
    public ExpectationVo getInfo() {
        Long id = Long.valueOf(StpUtil.getLoginId().toString());
        ExpectationEntity expectationEntity = this.getOne(new QueryWrapper<ExpectationEntity>().eq("user_id", id));
        ExpectationVo expectationVo = new ExpectationVo();
        expectationVo.setAmount(expectationEntity.getAmount());
        return expectationVo;
    }

}