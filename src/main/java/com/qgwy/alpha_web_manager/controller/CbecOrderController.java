package com.qgwy.alpha_web_manager.controller;


import com.qgwy.alpha_web_manager.bean.SysUser;
import com.qgwy.alpha_web_manager.dto.OrderDto;
import com.qgwy.alpha_web_manager.service.CbecOrderService;
import com.qgwy.alpha_web_manager.util.Query;
import com.qgwy.alpha_web_manager.util.R;
import com.qgwy.alpha_web_manager.util.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/getUnReadOrderAmount")
    @ResponseBody
    @ApiOperation(value = "查询最新未查看的新订单数",notes = "查询最新未查看的新订单数")
    public R getUnReadOrderAmount(HttpServletRequest request) {
        SysUser userInfo = UserUtils.getUserInfo(request);
        int amount = cbecOrderService.getUnReadOrderAmount(userInfo.getMarketId());
        return R.ok().put("unReadAmount",amount);
    }
    //统计新入订单数、未审核订单数、已审核订单数
    @ApiOperation(value = "查询三种状态订单的数量",notes = "统计新入订单数、未审核订单数、已审核订单数")
    @GetMapping("/getOrderNums")
    @ResponseBody
    public R getOrderNums(HttpServletRequest request) {
        SysUser userInfo = UserUtils.getUserInfo(request);
        Map<String,Integer> res = cbecOrderService.getOrderNums(userInfo.getMarketId());
        return R.ok().put("data",res);
    }

    //查询新入订单，将未读的订单放在前面，订单按时间倒序排序


    /**
     * currPage : 当前页
     * limit ： 每页的条数
     * type : 1（新入订单） 2（未审核订单） 3（已审核订单） 4(消息提醒)
     * sort : 排序的字段名 订单编号（order_sn） 提交日期（create_date）
     * order : desc (降序) asc(升序)
     * orderSn : 编号模糊搜索
     * @param map
     * @param request
     * @return
     */
    //查询订单列表（新入订单、未审核订单、已审核订单）
    //根据订单编号、提交时间排序
    //根据订单编号搜索
    @ApiOperation(value = "查询订单列表",notes = "新入订单、未审核订单、已审核订单")
    @GetMapping("/getOrderList")
    @ResponseBody
    public R list(@RequestParam Map<String,Object> map,HttpServletRequest request) {
        SysUser userInfo = UserUtils.getUserInfo(request);
        Query query = new Query(map);
        query.put("marketId",userInfo.getMarketId());
        List<OrderDto> orderDtoList = cbecOrderService.orderList(query);
        int total = cbecOrderService.total(query);
        return R.ok().put("data",orderDtoList).put("total",total);
    }

    //查询订单详情
    @ApiOperation(value = "查询订单详情",notes = "查询订单及关联商品列表")
    @GetMapping("/getOrderDetail")
    @ResponseBody
    public R getOrderDetail(Integer orderId) {
        OrderDto orderDetail = cbecOrderService.getOrderDetail(orderId);
        return R.ok().put("data",orderDetail);
    }


    //审核
    @ApiOperation(value = "审核订单",notes = "审核订单")
    @GetMapping("/checkOrder")
    @ResponseBody
    public R checkOrder(Integer orderId) {
        if(cbecOrderService.checkOrder(orderId)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 1、消减库存
     * 2、生成出库单
     * 3、将订单状态修改为已发货
     * @param orderId
     * @return
     */
    //订单发货
//    @ApiOperation(value = "订单发货",notes = "审核订单")
////    @GetMapping("/delivery")
////    @ResponseBody
////    public R delivery(Integer orderId) {
////
////    }
}
