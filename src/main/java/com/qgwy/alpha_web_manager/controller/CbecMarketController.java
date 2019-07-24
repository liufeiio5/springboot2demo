package com.qgwy.alpha_web_manager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qgwy.alpha_web_manager.bean.CbecMarket;
import com.qgwy.alpha_web_manager.service.CbecMarketService;
import com.qgwy.alpha_web_manager.util.R;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 超市信息表 前端控制器
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@RestController
@RequestMapping("/cbec-market")
public class CbecMarketController {

    @Autowired
    private CbecMarketService cbecMarketService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public R list(){
        List<CbecMarket> list = cbecMarketService.list();
        System.out.println(">>>>>>>>>>>>>>>>>>:"+list);
        return R.ok().put("data",list);
    }
}
