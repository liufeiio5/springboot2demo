package com.qgwy.alpha_web_manager.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qgwy.alpha_web_manager.bean.CbecArea;
import com.qgwy.alpha_web_manager.mapper.CbecAreaMapper;
import com.qgwy.alpha_web_manager.service.CbecAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 全国区域信息 服务实现类
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Service
public class CbecAreaServiceImpl extends ServiceImpl<CbecAreaMapper, CbecArea> implements CbecAreaService {

    @Autowired
    private CbecAreaMapper cbecAreaMapper;

    /**
     * 测试
     * @return
     */
    @Override
    public List<CbecArea> getList(){
        return cbecAreaMapper.selectList(new QueryWrapper<>());
    }
}
