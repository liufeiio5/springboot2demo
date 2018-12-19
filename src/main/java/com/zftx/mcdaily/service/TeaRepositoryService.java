package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.TeaRepository;

import java.util.ArrayList;
import java.util.Map;

public interface TeaRepositoryService {

    /**
     *  查询 下午茶仓库
     * @param teaRepository
     * @return
     */
    public ArrayList<Map<String,Object>> getTeaRepository(TeaRepository teaRepository);

   /**
     * 新增 下午茶仓库
     * @param teaRepository
     * @return
     */
    public String addTeaRepository(TeaRepository teaRepository);

    /**
     * 修改 下午茶仓库
     * @param teaRepository
     * @return
     */
    public String updateTeaRepository(TeaRepository teaRepository);

    /**
     * 删除 下午茶仓库
     * @param teaRepository
     * @return
     */
    public String delTeaRepository(TeaRepository teaRepository);
}
