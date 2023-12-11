package com.managexpr.jizhang.component;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.managexpr.jizhang.dao.ExpenseDao;
import com.managexpr.jizhang.dto.*;
import com.managexpr.jizhang.entity.CategoryEntity;
import com.managexpr.jizhang.entity.ExpectionEntity;
import com.managexpr.jizhang.entity.ExpenseEntity;
import com.managexpr.jizhang.entity.UserEntity;
import com.managexpr.jizhang.service.CategoryService;
import com.managexpr.jizhang.service.ExpectionService;
import com.managexpr.jizhang.service.ExpenseService;
import com.managexpr.jizhang.service.UserService;
import com.managexpr.jizhang.service.impl.ExpenseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduledTask {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseServiceImpl expenseServiceImpl;

    @Autowired
    private ExpectionService expectionService;

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseDao expenseDao;

    @Autowired
    private CategoryService categoryService;



    /**
     * 监测是否超过预期计划，超过发送短信提醒
     */
    @Scheduled(cron = "30 51 23 * * ?")
    public void scheduledTask(){
        List<Long> ids = new ArrayList<>();
        for (ExpectionEntity expectionEntity : expectionService.list()) {
            ids.add(expectionEntity.getUserId());
        }
        for (Long id : ids) {
            ExpectionEntity expectionEntity = expectionService.getOne(new QueryWrapper<ExpectionEntity>().eq("user_id", id));
            List<ExpenseEntity> expenseEntity = expenseDao.selectExpenseByMonth(id);


            List<ExpenseVo> expenseVos = expenseEntity.stream().map((expenseEn) -> {

                ExpenseVo expenseVo = new ExpenseVo();
                expenseVo.setAmount(expenseEn.getAmount());
                expenseVo.setRemark(expenseEn.getRemark());

                //获取用户id
                //Long id = Long.valueOf(StpUtil.getLoginId().toString());

                //获取用户名
                UserEntity user = userService.getById(expenseEn.getUserId());
//                expenseVo.setUsername(user.getUsername());

                //获取分类名
                CategoryEntity category = categoryService.getById(expenseEn.getCategoryId());
                expenseVo.setCategoryName(category.getName());

                return expenseVo;
            }).collect(Collectors.toList());

            Long amount = 0L;

            for (ExpenseVo expenseVo : expenseVos) {
                amount += expenseVo.getAmount();
            }


            UserEntity user = userService.getById(id);
            if (amount < (expectionEntity.getAmount() + 500)) {

                MailDto mailDto = new MailDto();
                mailDto.setRecipient(user.getEmail());
                mailDto.setSubject("邮箱提醒");
                mailDto.setContent("你实际支出即将超出您的预期");

                mailUtils.sendSimpleMail(mailDto);
            }
        }
    }
}
