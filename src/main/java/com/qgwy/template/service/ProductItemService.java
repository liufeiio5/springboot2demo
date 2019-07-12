package com.qgwy.template.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qgwy.template.bean.ProductItem;
import com.qgwy.template.vo.ProductDetailVo;

public interface ProductItemService extends IService<ProductItem> {

    Page<ProductDetailVo> getProductDetail(Page<ProductDetailVo> page, Integer marketId, Integer categoryId);

    /**
     * mybatis-plus自带的单表分页方法
     * @param page
     * @param marketId
     * @return
     */
    IPage<ProductItem> getProductItemList(Page<ProductItem> page, Integer marketId);
}
