package com.qgwy.alpha_web_manager.controller;


import com.qgwy.alpha_web_manager.service.CbecConsumeService;
import com.qgwy.alpha_web_manager.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@RestController
@RequestMapping("/cbec-consume")
public class CbecConsumeController {

    @Autowired
    private CbecConsumeService cbecConsumeService;

    @GetMapping(value = "/getConsume")
    @ResponseBody
    public R getConsume(){
        return R.ok().put("data",cbecConsumeService.list());
    }
}