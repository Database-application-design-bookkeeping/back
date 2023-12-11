package com.hardews.jizhang.controller;

import com.hardews.jizhang.dto.ExpectationVo;
import com.hardews.jizhang.service.ExpectationService;
import com.hardews.jizhang.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("jizhang/expectation")
public class ExpectationController {
    @Autowired
    private ExpectationService expectationService;
    @GetMapping("/info")
    public R info(){
		ExpectationVo expectationVo = expectationService.getInfo();
        return R.ok("查询成功").put("data", expectationVo);
    }

    @GetMapping("/save")
    public R save(@RequestParam Long amount){
		expectationService.save(amount);
        return R.ok("保存成功");
    }

    @GetMapping("/update")
    public R update(@RequestParam Long amount){
		expectationService.updateExpectation(amount);
        return R.ok("修改成功");
    }
}
