package com.qgwy.template.service;


import com.qgwy.template.bean.Town;

public interface TownService {

    Town getTownList(Integer id);


    /**
     * 根据主键id修改内容
     * @param name
     * @param id
     * @return
     */
    Town updateTown(String name,Integer id);


    /**
     * 根据主键删除
     * @param id
     * @return
     */
    Integer deleteTownById(Integer id);

    /**
     * 删除cacheName下的所有数据
     */
    void deleteAllEntries();
}
