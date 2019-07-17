package com.qgwy.template.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qgwy.template.bean.ProductItem;
import com.qgwy.template.vo.ItemListVo;
import com.qgwy.template.vo.ProductDetailVo;
import io.swagger.models.auth.In;

public interface ProductItemService extends IService<ProductItem> {

    Page<ProductDetailVo> getProductDetail(Page<ProductDetailVo> page, Integer marketId, Integer categoryId);

    /**
     * mybatis-plus自带的单表分页方法
     * @param page
     * @param marketId
     * @return
     */
    IPage<ProductItem> getProductItemList(Page<ProductItem> page, Integer marketId);

    /**
     * 定义一个单表分页
     * @param page 分页条件
     * @return 返回分页后的数据，是一个Object对象
     */
    IPage<ProductItem> getItemList(Page page);

    /**
     * mybatis-plus自带的单表分页方法
     * @param offset 当前页
     * @param pageNumber 每一页的数据量
     * @param marketId 超市ID
     * @param categoryId　分类ID
     * @return
     */
    Page<ItemListVo> getItemListVo(Integer offset,Integer pageNumber, Integer marketId, Integer categoryId);

    /**
     * 查询product表查询所需字段的信息
     * @param currPage 当前第几页
     * @param size 每一页数据大小
     * @param categoryId 查询条件：分类Id
     * @return
     */
    IPage<ProductItem> getProductItemInfo(Integer currPage,Integer size,Integer categoryId);
}
