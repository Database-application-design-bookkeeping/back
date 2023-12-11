package com.hardews.jizhang.controller;

import com.hardews.jizhang.dto.ExceptionVo;
import com.hardews.jizhang.service.ExceptionService;
import com.hardews.jizhang.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("jizhang/exception")
public class ExceptionController {
    @Autowired
    private ExceptionService exceptionService;
    @GetMapping("/info")
    public R info(){
		ExceptionVo exceptionVo = exceptionService.getInfo();
        return R.ok("查询成功").put("data", exceptionVo);
    }

    @GetMapping("/save")
    public R save(@RequestParam Long amount){
		exceptionService.save(amount);
        return R.ok("保存成功");
    }

    @GetMapping("/update")
    public R update(@RequestParam Long amount){
		exceptionService.updateException(amount);
        return R.ok("修改成功");
    }
}
