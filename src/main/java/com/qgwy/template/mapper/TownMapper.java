package com.qgwy.template.mapper;


import com.qgwy.template.bean.Town;
import org.springframework.stereotype.Repository;

@Repository
public interface TownMapper {

    /**
     * 根据主键ID查询
     * @param id
     * @return
     */
    Town getTownList(Integer id);

    /**
     * 根据主键id修改内容
     * @param name
     * @param id
     * @return
     */
    Integer updateTown(String name,Integer id);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    Integer deleteTownById(Integer id);
}
