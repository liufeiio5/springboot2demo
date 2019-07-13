package com.qgwy.template.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qgwy.template.bean.ProductItem;
import com.qgwy.template.service.ProductItemService;
import com.qgwy.template.util.R;
import com.qgwy.template.vo.ProductDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/productItem")
@Api(tags = {"mybatis-plus分页测试"})
public class ProductItemController {

    @Autowired
    private ProductItemService productItemService;


    @ResponseBody
    @PostMapping("/getProductDetails")
    @ApiOperation(value = "多表联查分页",notes = "查询商品的详细信息")
    public R getProductDetails(Integer page,Integer size,Integer marketId,Integer categoryId){
        Map<String,Object> map = new HashMap<>();
        Page<ProductDetailVo> page1 = new Page<>(page,size);
        Page<ProductDetailVo> detailVoPage = productItemService.getProductDetail(page1,marketId,categoryId);
        map.put("data",detailVoPage);
        return R.ok().put("data",detailVoPage);
    }

    @ResponseBody
    @PostMapping("/getProductItemList")
    @ApiOperation(value = "单表分页，使用mybatis-plus自带的分页",notes = "单表查询商品的基本信息")
    public R getProductItemList(Integer page, Integer size, Integer marketId){
        Map<String,Object> map = new HashMap<>();
        IPage<ProductItem> productItemPage = productItemService.getProductItemList(new Page<>(page,size),marketId);
        List<ProductItem> list = productItemPage.getRecords();
        map.put("data",productItemPage);
        return R.ok().put("data",productItemPage);
    }
}
