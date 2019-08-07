package com.project.template.service.Impl;


import com.project.template.bean.Town;
import com.project.template.mapper.TownMapper;
import com.project.template.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "town")
public class TownServiceImpl implements TownService {

    @Autowired
    private TownMapper townMapper;


    @Override
    @Cacheable(key = "'getTownList_'+#id")
    public Town getTownList(Integer id){
        return townMapper.getTownList(id);
    }

    /**
     * 根据主键id修改内容
     * @param name
     * @param id
     * @return
     */
    @Override
    @CachePut(key = "'getTownList_'+#id",unless = "#result==null")
    public Town updateTown(String name,Integer id){
        Integer result = townMapper.updateTown(name,id);
        if(result > 0){
            return townMapper.getTownList(id);
        }else{
            return null;
        }
    }

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    @Override
    @CacheEvict(key = "'getTownList_'+#id")
    public Integer deleteTownById(Integer id){
        return townMapper.deleteTownById(id);
    }

    /**
     * 删除cacheName下的所有数据
     */
    @Override
    @CacheEvict(cacheNames = "town",allEntries = true)
    public void deleteAllEntries(){

    }
}
