package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.TeaChoose;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Map;

public interface TeaChooseService {


    /**
     *  查询 茶点选餐
     * @param teaChoose
     * @return
     */
    public ArrayList<Map<String,Object>> getTeaChoose(@Param("teaRepository") TeaChoose teaChoose);

    /**
     * 新增 茶点选餐
     * @param teaChoose
     * @return
     */
    public String addTeaChoose(@Param("teaRepository") TeaChoose teaChoose);

    /**
     * 修改 茶点选餐
     * @param teaChoose
     * @return
     */
    public String updateTeaChoose(@Param("teaRepository") TeaChoose teaChoose);

    /**
     * 删除 茶点选餐
     * @param teaChoose
     * @return
     */
    public String delTeaChoose(@Param("teaRepository") TeaChoose teaChoose);

    /**
     * 花费不能超过10茶币
     * @param teaChoose
     * @param teaPrice
     * @return
     */
    public String isBeOutTenMoney(@Param("teaRepository") TeaChoose teaChoose,Integer teaPrice);
}
