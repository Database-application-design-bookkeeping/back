package com.managexpr.jizhang.controller;

import java.util.Arrays;
import java.util.Map;

import com.managexpr.jizhang.dto.ExpectionVo;
import com.managexpr.jizhang.utils.PageUtils;
import com.managexpr.jizhang.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.managexpr.jizhang.entity.ExpectionEntity;
import com.managexpr.jizhang.service.ExpectionService;

@RestController
@RequestMapping("jizhang/expection")
public class ExpectionController {
    @Autowired
    private ExpectionService expectionService;
    @GetMapping("/info")
    public R info(){
		ExpectionVo expectionVo = expectionService.getInfo();
        return R.ok("查询成功").put("data", expectionVo);
    }

    @GetMapping("/save")
    public R save(@RequestParam Long amount){
		expectionService.save(amount);
        return R.ok("保存成功");
    }

    @GetMapping("/update")
    public R update(@RequestParam Long amount){
		expectionService.updateExpection(amount);
        return R.ok("修改成功");
    }
}
