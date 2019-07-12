package com.qgwy.template.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qgwy.template.bean.ProductItem;
import com.qgwy.template.mapper.ProductItemMapper;
import com.qgwy.template.service.ProductItemService;
import com.qgwy.template.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductItemServiceImpl extends ServiceImpl<ProductItemMapper,ProductItem> implements ProductItemService {

    @Autowired
    private ProductItemMapper productItemMapper;

    @Override
    public Page<ProductDetailVo> getProductDetail(Page<ProductDetailVo> page, Integer marketId, Integer categoryId){
        return page.setRecords(this.productItemMapper.getProductDetails(page,marketId,categoryId));
    }


    public IPage<ProductItem> getProductItemList(Page<ProductItem> page, Integer marketId){
        //条件构造器，单表查询数据
        QueryWrapper<ProductItem> queryWrapper = new QueryWrapper<ProductItem>()
                .eq("market_id",marketId)
                .select("product_id","product_name","product_cost_price","product_price","create_date");
        return this.productItemMapper.selectPage(page,queryWrapper);

    }

}
