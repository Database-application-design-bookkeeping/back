package com.hardews.jizhang.controller;

import java.util.ArrayList;

import com.hardews.jizhang.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hardews.jizhang.service.CategoryService;

@RestController
@RequestMapping("jizhang/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/outcome")
    public R outcome(){
        ArrayList list = categoryService.Outcome();
        return R.ok("查询成功").put("page", list);
    }

    @GetMapping("/income")
    public R income(){
        ArrayList list = categoryService.Income();
        return R.ok("查询成功").put("page", list);
    }
}
