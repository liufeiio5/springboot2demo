package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Summary;
import com.zftx.mcdaily.bean.Weekly;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public interface SummaryMapper {

    /**
     * 查询 周小结
     * @param summary
     * @return
     */
    public List<Summary> getSummary(@Param("summary") Summary summary);

    /**
     * 添加 周小结
     * @param summary
     * @return
     */
    public Integer addSummary(@Param("summary") Summary summary);


    /**
     * 删除 周小结
     * @param summary
     * @return
     */
    public Integer deleteSummary(@Param("summary") Summary summary);

    /**
     * 修改 周小结
     * @param summary
     * @return
     */
    public Integer updateSummary(@Param("summary") Summary summary);
}
