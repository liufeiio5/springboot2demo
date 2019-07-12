package com.qgwy.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.qgwy.template.bean.ProductItem;
import com.qgwy.template.vo.ProductDetailVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductItemMapper extends BaseMapper<ProductItem> {

    /**
     * 查询商品的详细信息
     * @param page
     * @param marketId
     * @param categoryId
     * @return
     */
    List<ProductDetailVo> getProductDetails(Page page,
                                            @Param("marketId") Integer marketId,
                                            @Param("categoryId") Integer categoryId);


}
