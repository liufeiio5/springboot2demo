package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Type;
import com.zftx.mcdaily.bean.User;

import java.util.List;

public interface TypeService {

    /**
     * 查   类型
     * @param type
     * @return
     */
    public List<Type> getType(Type type);

    /**
     * 增  类型
     * @param type
     * @return
     */
    public String insertType(Type type);

    /**
     * 改  类型
     * @param type
     * @return
     */
    public String updateType(Type type);

    /**
     * 实际删 类型
     * @param type
     * @return
     */
    public String deleteType(Type type);
}
