package com.qgwy.alpha_web_manager.controller;


import com.qgwy.alpha_web_manager.service.CbecProductItemService;
import com.qgwy.alpha_web_manager.util.R;
import com.qgwy.alpha_web_manager.vo.MarketingManagementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@RestController
@RequestMapping("/cbec-product-item")
public class CbecProductItemController {

    @Autowired
    private CbecProductItemService productItemService;

    @GetMapping(value = "/getMarketingManagementDetails")
    @ResponseBody
    public R getMarketingManagementDetails(Integer currPage,Integer size,Integer isOnSell,Integer isRecommend){
        List<MarketingManagementVo> managementVos = productItemService.productItemList(currPage,size,isOnSell,isRecommend);
        return R.ok().put("data",managementVos);
    }
}
