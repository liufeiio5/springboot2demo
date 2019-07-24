package com.qgwy.alpha_web_manager.service;

import com.qgwy.alpha_web_manager.bean.CbecArea;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 全国区域信息 服务类
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
public interface CbecAreaService extends IService<CbecArea> {

    /**
     * 测试
     * @return
     */
    List<CbecArea> getList();
}
