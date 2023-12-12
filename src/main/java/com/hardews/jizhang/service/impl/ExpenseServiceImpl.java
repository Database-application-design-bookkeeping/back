package com.hardews.jizhang.service.impl;

import com.hardews.jizhang.dao.ExpenseDao;
import com.hardews.jizhang.dto.ExpenseDto;
import com.hardews.jizhang.dto.ExpenseTotalVo;
import com.hardews.jizhang.dto.ExpenseVo;
import com.hardews.jizhang.entity.AccountEntity;
import com.hardews.jizhang.entity.CategoryEntity;
import com.hardews.jizhang.entity.UserEntity;
import com.hardews.jizhang.service.AccountService;
import com.hardews.jizhang.service.CategoryService;
import com.hardews.jizhang.service.UserService;
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


import com.hardews.jizhang.entity.ExpenseEntity;
import com.hardews.jizhang.service.ExpenseService;


@Service("expenseService")
public class ExpenseServiceImpl extends ServiceImpl<ExpenseDao, ExpenseEntity> implements ExpenseService {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;


    @Autowired
    AccountService accountService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExpenseEntity> page = this.page(
                new Query<ExpenseEntity>().getPage(params),
                new QueryWrapper<ExpenseEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public ExpenseVo getExpense() {
        //获取用户id
        Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());

        ExpenseEntity expense = this.getOne(new QueryWrapper<ExpenseEntity>().eq("user_id", id));

        UserEntity user = userService.getById(id);

        ExpenseVo expenseVo = new ExpenseVo();
        BeanUtils.copyProperties(expense,expenseVo);

//        expenseVo.setUsername(user.getUsername());

        return expenseVo;
    }

    @Override
    public void saveExp(ExpenseDto expenseDto) {

        //获取用户id
        Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());

        ExpenseEntity expenseEntity = new ExpenseEntity();
        BeanUtils.copyProperties(expenseDto,expenseEntity);
        expenseEntity.setUserId(id);
        expenseEntity.setCreateTime(new Date());
        expenseEntity.setUpdateTime(new Date());

        AccountEntity account = accountService.getOne(new QueryWrapper<AccountEntity>().eq("user_id",id));
        account.setBalance(account.getBalance()-expenseDto.getAmount());
        account.setUpdateTime(new Date());

        accountService.updateById(account);

        this.save(expenseEntity);
    }

    @Override
    public ExpenseTotalVo getExpenseByDay() {

        //用户id
        Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());
        List<ExpenseEntity> expenseEntity = this.baseMapper.selectExpenseByDay(id);
        return count(expenseEntity);
    }

    @Override
    public ExpenseTotalVo getExpenseByWeek() {
        //用户id
        Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());
        List<ExpenseEntity> expenseEntity = this.baseMapper.selectExpenseByWeek(id);
        return count(expenseEntity);
    }

    @Override
    public ExpenseTotalVo getExpenseByMonth() {
        //用户id
        Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());
        List<ExpenseEntity> expenseEntity = this.baseMapper.selectExpenseByMonth(id);
        return count(expenseEntity);
    }

    //封装一个用来返回结果的函数
    public ExpenseTotalVo count(List<ExpenseEntity> expenseEntity){
        //总金额
        Long total = 0L;

        List<ExpenseVo> expenseVos = expenseEntity.stream().map((expenseEn) -> {

            ExpenseVo expenseVo = new ExpenseVo();
            expenseVo.setAmount(expenseEn.getAmount());
            expenseVo.setRemark(expenseEn.getRemark());
            expenseVo.setOutput_method(expenseEn.getOutput_method());

            //获取用户id
            Long id = Long.valueOf(JwtPayloadHolder.getClaims().get("id").asString());

//            //获取用户名
            UserEntity user = userService.getById(id);
//            expenseVo.setUsername(user.getUsername());
            expenseVo.setCreateTime(user.getCreateTime());

            //获取分类名
            CategoryEntity category = categoryService.getById(expenseEn.getCategoryId());
            expenseVo.setCategoryName(category.getName());

            return expenseVo;
        }).collect(Collectors.toList());

        for (ExpenseVo expenseVo : expenseVos) {
            total += expenseVo.getAmount();
        }

        ExpenseTotalVo expenseTotalVo = new ExpenseTotalVo();
        expenseTotalVo.setTotal(total);
        expenseTotalVo.setExpenseVos(expenseVos);

        return expenseTotalVo;
    }

}