package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.TeaRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface TeaRepositoryMapper {


    /**
     *  查询 下午茶仓库
     * @param teaRepository
     * @return
     */
    public ArrayList<Map<String,Object>> getTeaRepository(@Param("teaRepository") TeaRepository teaRepository,@Param("flag")String flag,@Param("lowPrice")Float lowPrice,@Param("highPrice")Float highPrice);

    /**
     *  查询 茶点类型
     * @return
     */
    public List<TeaRepository> getTeaRepositoryCatName();

    /**
     * 新增 下午茶仓库
     * @param teaRepository
     * @return
     */
    public Integer addTeaRepository(@Param("teaRepository") TeaRepository teaRepository);

    /**
     * 修改 下午茶仓库
     * @param teaRepository
     * @return
     */
    public Integer updateTeaRepository(@Param("teaRepository") TeaRepository teaRepository);

    /**
     * 删除 下午茶仓库
     * @param teaRepository
     * @return
     */
    public Integer delTeaRepository(@Param("teaRepository") TeaRepository teaRepository);

}
