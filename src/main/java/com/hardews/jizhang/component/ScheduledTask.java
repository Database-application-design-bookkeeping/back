package com.hardews.jizhang.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hardews.jizhang.dao.ExpenseDao;
import com.hardews.jizhang.dto.ExpenseVo;
import com.hardews.jizhang.dto.MailDto;
import com.hardews.jizhang.dto.*;
import com.hardews.jizhang.entity.CategoryEntity;
import com.hardews.jizhang.entity.ExceptionEntity;
import com.hardews.jizhang.entity.ExpenseEntity;
import com.hardews.jizhang.entity.UserEntity;
import com.hardews.jizhang.service.CategoryService;
import com.hardews.jizhang.service.ExceptionService;
import com.hardews.jizhang.service.ExpenseService;
import com.hardews.jizhang.service.UserService;
import com.hardews.jizhang.service.impl.ExpenseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ExceptionService exceptionService;

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
        for (ExceptionEntity exceptionEntity : exceptionService.list()) {
            ids.add(exceptionEntity.getUserId());
        }
        for (Long id : ids) {
            ExceptionEntity exceptionEntity = exceptionService.getOne(new QueryWrapper<ExceptionEntity>().eq("user_id", id));
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
            if (amount < (exceptionEntity.getAmount() + 500)) {

                MailDto mailDto = new MailDto();
                mailDto.setRecipient(user.getEmail());
                mailDto.setSubject("邮箱提醒");
                mailDto.setContent("你实际支出即将超出您的预期");

                mailUtils.sendSimpleMail(mailDto);
            }
        }
    }
}
