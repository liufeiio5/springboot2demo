package com.qgwy.alpha_web_manager.service;

import com.qgwy.alpha_web_manager.bean.CbecProductItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qgwy.alpha_web_manager.vo.MarketingManagementVo;

import java.util.List;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
public interface CbecProductItemService extends IService<CbecProductItem> {

    /**
     * 商品列表
     * @param currPage
     * @param size
     * @return
     */
    List<MarketingManagementVo> productItemList(Integer currPage, Integer size, Integer isOnSell, Integer isRecommend);

}
