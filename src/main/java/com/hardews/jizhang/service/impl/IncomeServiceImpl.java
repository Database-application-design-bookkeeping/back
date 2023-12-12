package com.hardews.jizhang.service.impl;



import com.hardews.jizhang.dao.IncomeDao;
import com.hardews.jizhang.dto.IncomeDto;
import com.hardews.jizhang.dto.IncomeTotalVo;
import com.hardews.jizhang.dto.IncomeVo;
import com.hardews.jizhang.entity.AccountEntity;
import com.hardews.jizhang.entity.CategoryEntity;
import com.hardews.jizhang.service.AccountService;
import com.hardews.jizhang.service.CategoryService;
import com.hardews.jizhang.utils.JwtPayloadHolder;
import com.hardews.jizhang.utils.PageUtils;
import com.hardews.jizhang.utils.Query;
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


import com.hardews.jizhang.entity.IncomeEntity;
import com.hardews.jizhang.service.IncomeService;
import org.springframework.util.ObjectUtils;


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
        long id = Long.parseLong(JwtPayloadHolder.getClaims().get("id").asString());
        incomeEntity.setCreateTime(new Date());
        incomeEntity.setUserId(id);

        AccountEntity account = accountService.getOne(new QueryWrapper<AccountEntity>().eq("user_id", id));
        account.setBalance(account.getBalance()+incomeDto.getAmount());
        account.setUpdateTime(new Date());

        incomeEntity.setAccountId(Long.valueOf(account.getId()));
        accountService.updateById(account);
        this.save(incomeEntity);
    }

    @Override
    public IncomeTotalVo getIncomeByDay() {

        //用户id
        Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());

        List<IncomeEntity> incomeEntities = this.baseMapper.selectIncomeByDay(id);

        return count(incomeEntities);
    }

    @Override
    public IncomeTotalVo getIncomeByWeek() {
        //用户id
        Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());

        List<IncomeEntity> incomeEntities = this.baseMapper.selectIncomeByWeek(id);

        return count(incomeEntities);
    }

    @Override
    public IncomeTotalVo getIncomeByMonth() {
        //用户id
        Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());

        List<IncomeEntity> incomeEntities = this.baseMapper.selectIncomeByMonth(id);

        return count(incomeEntities);
    }

    @Override
    public IncomeTotalVo getIncomeTotal() {
        //用户id
        Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());

        List<IncomeEntity> incomeEntities = this.baseMapper.selectIncomeTotal(id);

        return count(incomeEntities);
    }

    public IncomeTotalVo count(List<IncomeEntity> incomeEntities){

        Long count = 0L;

        List<IncomeVo> incomeVos = incomeEntities.stream().map(incomeEntity -> {
            //用户id
            Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());

            IncomeVo incomeVo = new IncomeVo();
            incomeVo.setAmount(incomeEntity.getAmount());
            incomeVo.setRemark(incomeEntity.getRemark());
            incomeVo.setCreateTime(incomeEntity.getCreateTime());
            incomeVo.setInputMethod(incomeEntity.getInputMethod());

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