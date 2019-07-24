package com.qgwy.alpha_web_manager.controller;


import com.qgwy.alpha_web_manager.bean.SysUser;
import com.qgwy.alpha_web_manager.service.CbecOrderService;
import com.qgwy.alpha_web_manager.util.R;
import com.qgwy.alpha_web_manager.util.UserUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/cbec-order")
public class CbecOrderController {
    @Autowired
    private CbecOrderService cbecOrderService;

    //查询未读的订单数量
    public R getUnReadOrderAmount(HttpServletRequest request) {
        SysUser userInfo = UserUtils.getUserInfo(request);
        return R.ok();
    }

    //查询新入订单，将未读的订单放在前面，订单按时间倒序排序

    //统计新入订单数、未审核订单数、已审核订单数

    //查询订单列表（新入订单、未审核订单、已审核订单）
    //根据订单编号、提交时间排序
    //根据订单编号搜索

    //查询订单详情

    //审核

    //发货

}
