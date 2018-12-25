package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.TeaChoose;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
public interface TeaChooseMapper {


    /**
     *  查询 茶点选餐
     * @param teaChoose
     * @return
     */
    public ArrayList<Map<String,Object>> getTeaChoose(@Param("teaChoose") TeaChoose teaChoose);

    /**
     * 新增 茶点选餐
     * @param teaChoose
     * @return
     */
    public Integer addTeaChoose(@Param("teaChoose") TeaChoose teaChoose);

    /**
     * 修改 茶点选餐
     * @param teaChoose
     * @return
     */
    public Integer updateTeaChoose(@Param("teaChoose") TeaChoose teaChoose);

    /**
     * 删除 茶点选餐
     * @param teaChoose
     * @return
     */
    public Integer delTeaChoose(@Param("teaChoose") TeaChoose teaChoose);

    /**
     * 所选茶点总费不超过10茶币
     * @param teaChoose
     * @return
     */
    public Integer isBeOutTenMoney(@Param("teaChoose") TeaChoose teaChoose);


    /**
     * 茶点总量统计
     * @return
     */
    public ArrayList<Map<String,Object>> getTeaStatistics(@Param("teaChoose")TeaChoose teaChoose,@Param("tName")String tName);

    /**
     * 茶点分发
     * @return
     */
    public ArrayList<Map<String,Object>> getTeaDistribute(@Param("teaChoose")TeaChoose teaChoose,@Param("fullName")String fullName);

    /**
     * 获取所有选餐人
     * @return
     */
    public ArrayList<Map<String,Object>> getTeaUser(@Param("teaChoose")TeaChoose teaChoose,@Param("fullName")String fullName);
}
