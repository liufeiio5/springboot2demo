package com.qgwy.template.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qgwy.template.bean.ProductItem;
import com.qgwy.template.service.ProductItemService;
import com.qgwy.template.util.R;
import com.qgwy.template.vo.ItemListVo;
import com.qgwy.template.vo.ProductDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("返回结果：/n{},成功,哈哈！！！",detailVoPage);
        return R.ok().put("data",detailVoPage);
    }

    @ResponseBody
    @PostMapping("/getProductItemList")
    @ApiOperation(value = "单表分页，使用mybatis-plus自带的分页",notes = "单表查询商品的基本信息")
    public R getProductItemList(Integer page, Integer size, Integer marketId){
        IPage<ProductItem> productItemPage = productItemService.getProductItemList(new Page<>(page,size),marketId);
        List<ProductItem> list = productItemPage.getRecords();
        return R.ok().put("data",productItemPage);
    }

    @ResponseBody
    @GetMapping(value = "/getItemList")
    @ApiOperation(value = "单表分页，使用mybatis-plus自带分页工具",notes = "查询商品并惊醒分页")
    public R getItemList(Integer page,Integer size){
        Page page1 = new Page(page,size);
        IPage<ProductItem> itemIPage = productItemService.getItemList(page1);
        if(itemIPage != null){
            System.out.println(itemIPage.getRecords().get(0).getCreateDate());
            return R.ok().put("pages",itemIPage.getPages())
                    .put("size",itemIPage.getSize())
                    .put("total",itemIPage.getTotal())
                    .put("current",itemIPage.getCurrent())
                    .put("data",itemIPage.getRecords());
        }else{
            return R.error("没有数据");
        }
    }

    @ResponseBody
    @GetMapping(value = "/getItemListVo")
    @ApiOperation(value = "多表查询分页，使用mybatis-plus自带分页工具",notes = "查询商品的信息")
    public R getItemListVo(Integer offset,Integer pageNumber,Integer marketId,Integer categoryId){
//        if(currPage == null){
//            currPage = 1;
//        }
//        if(size == null){
//            size = 5;
//        }
//        if(marketId == null){
//            marketId = 1;
//        }
//        if(categoryId == null){
//            categoryId = 1;
//        }
        //调用service接口，返回一个分页对象，里面包含分页数据的各种信息
        Page<ItemListVo> listVoPage = productItemService.getItemListVo(offset,pageNumber,marketId,categoryId);
        System.out.println("AAAA>>>>>>>>>>>>>>>>>>>>>>>>:"+offset);
        System.out.println("BBBBB>>>>>>>>>>>>>>>>>>>>>>:"+pageNumber);
        return R.ok().put("pages",listVoPage.getPages())
                .put("size",listVoPage.getSize())
                .put("total",listVoPage.getTotal())
                .put("current",listVoPage.getCurrent())
                .put("rows",listVoPage.getRecords());
    }

    @GetMapping("test")
    public String getIndex(){
        return "test";
    }

    @RequestMapping("/mybatis")
    public String getMybatis(){
        return "mybatis";
    }
    @RequestMapping("/mybatis_plus")
    public String getMybatis_plus(){
        return "mybatis_plus";
    }


    @ResponseBody
    @GetMapping(value = "/getProductItemInfo")
    @ApiOperation(value = "使用条件构造器进行筛选查询，并进行分页",notes = "查询商品的信息")
    public R getProductItemInfo(Integer currPage,Integer size,Integer categoryId){

        IPage<ProductItem> productItemIPage = productItemService.getProductItemInfo(currPage,size,categoryId);
        if(productItemIPage != null){
            return R.ok().put("total",productItemIPage.getTotal())
                    .put("current",productItemIPage.getCurrent())
                    .put("pages",productItemIPage.getPages())
                    .put("size",productItemIPage.getSize())
                    .put("data",productItemIPage.getRecords())
                    .put("currDataSize",productItemIPage.getRecords().size());
        }else{
            return R.error("没有数据");
        }
    }
}
