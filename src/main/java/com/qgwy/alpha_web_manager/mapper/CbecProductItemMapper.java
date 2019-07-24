package com.qgwy.alpha_web_manager.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qgwy.alpha_web_manager.bean.CbecProductItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qgwy.alpha_web_manager.vo.MarketingManagementVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品信息表 Mapper 接口
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
public interface CbecProductItemMapper extends BaseMapper<CbecProductItem> {

    /**
     * 获取营销商品详情
     * @param page
     * @param isOnSell
     * @param isRecommend
     * @return
     */
    List<MarketingManagementVo> getMarketingManagementDetails(Page page, @Param("isOnSell") Integer isOnSell,
                                                              @Param("isRecommend") Integer isRecommend);
}
