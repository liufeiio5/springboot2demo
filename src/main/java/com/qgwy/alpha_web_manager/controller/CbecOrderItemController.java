package com.qgwy.alpha_web_manager.controller;


import com.qgwy.alpha_web_manager.service.CbecOrderItemService;
import com.qgwy.alpha_web_manager.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单明细表 前端控制器
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@RestController
@RequestMapping("/cbec-order-item")
public class CbecOrderItemController {

    @Autowired
    private CbecOrderItemService cbecOrderItemService;

    @GetMapping(value = "/list")
    @ResponseBody
    public R list(){
        return R.ok().put("data",cbecOrderItemService.list());
    }
}
