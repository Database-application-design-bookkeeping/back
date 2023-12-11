package com.managexpr.jizhang.service.impl;




import cn.dev33.satoken.stp.StpUtil;
import com.managexpr.jizhang.dto.IncomeDto;
import com.managexpr.jizhang.dto.IncomeTotalVo;
import com.managexpr.jizhang.dto.IncomeVo;
import com.managexpr.jizhang.entity.AccountEntity;
import com.managexpr.jizhang.entity.CategoryEntity;
import com.managexpr.jizhang.service.AccountService;
import com.managexpr.jizhang.service.CategoryService;
import com.managexpr.jizhang.utils.PageUtils;
import com.managexpr.jizhang.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.managexpr.jizhang.dao.IncomeDao;
import com.managexpr.jizhang.entity.IncomeEntity;
import com.managexpr.jizhang.service.IncomeService;


@Service("incomeService")
public class IncomeServiceImpl extends ServiceImpl<IncomeDao, IncomeEntity> implements IncomeService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<IncomeEntity> page = this.page(
                new Query<IncomeEntity>().getPage(params),
                new QueryWrapper<IncomeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveIncome(IncomeDto incomeDto) {
        IncomeEntity incomeEntity = new IncomeEntity();
        BeanUtils.copyProperties(incomeDto,incomeEntity);

        //获取用户
        Long id = Long.valueOf(StpUtil.getLoginId().toString());
        incomeEntity.setUserId(id);
        incomeEntity.setCreateTime(new Date());

        AccountEntity account = accountService.getOne(new QueryWrapper<AccountEntity>().eq("user_id", id));
        account.setBalance(account.getBalance()+incomeDto.getAmount());
        account.setUpdateTime(new Date());

        accountService.updateById(account);

        this.save(incomeEntity);
    }

    @Override
    public IncomeTotalVo getIncomeByDay() {

        //用户id
        Long id = Long.valueOf(StpUtil.getLoginId().toString());

        List<IncomeEntity> incomeEntities = this.baseMapper.selectIncomeByDay(id);

        return count(incomeEntities);
    }

    @Override
    public IncomeTotalVo getIncomeByWeek() {
        //用户id
        Long id = Long.valueOf(StpUtil.getLoginId().toString());

        List<IncomeEntity> incomeEntities = this.baseMapper.selectIncomeByWeek(id);

        return count(incomeEntities);
    }

    @Override
    public IncomeTotalVo getIncomeByMonth() {
        //用户id
        Long id = Long.valueOf(StpUtil.getLoginId().toString());

        List<IncomeEntity> incomeEntities = this.baseMapper.selectIncomeByMonth(id);

        return count(incomeEntities);
    }

    public IncomeTotalVo count(List<IncomeEntity> incomeEntities){

        Long count = 0L;

        List<IncomeVo> incomeVos = incomeEntities.stream().map(incomeEntity -> {
            //用户id
            Long id = Long.valueOf(StpUtil.getLoginId().toString());

            IncomeVo incomeVo = new IncomeVo();
            incomeVo.setAmount(incomeEntity.getAmount());
            incomeVo.setRemark(incomeEntity.getRemark());
            incomeVo.setCreateTime(incomeEntity.getCreateTime());
            incomeVo.setInput_method(incomeEntity.getInput_method());

            //获取分类名
            CategoryEntity category = categoryService.getById(incomeEntity.getCategoryId());
            incomeVo.setCategoryName(category.getName());
            return incomeVo;


        }).collect(Collectors.toList());

        for (IncomeVo incomeVo : incomeVos) {

            count += incomeVo.getAmount();
        }

        IncomeTotalVo incomeTotalVo = new IncomeTotalVo();
        incomeTotalVo.setTotal(count);
        incomeTotalVo.setIncomeVos(incomeVos);

        return incomeTotalVo;
    }

}