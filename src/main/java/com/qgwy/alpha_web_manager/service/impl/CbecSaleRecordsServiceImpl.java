package com.qgwy.alpha_web_manager.service.impl;

import com.qgwy.alpha_web_manager.bean.CbecSaleRecords;
import com.qgwy.alpha_web_manager.mapper.CbecSaleRecordsMapper;
import com.qgwy.alpha_web_manager.service.CbecSaleRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 线下销售记录表（用来出库查询查询，对应线下销售商品详情表，一个线下销售记录对应多条商品详情） 服务实现类
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Service
public class CbecSaleRecordsServiceImpl extends ServiceImpl<CbecSaleRecordsMapper, CbecSaleRecords> implements CbecSaleRecordsService {

}
