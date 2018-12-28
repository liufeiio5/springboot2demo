package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.Summary;
import java.util.List;

public interface SummaryService {

    /**
     * 查询 周小结
     * @param summary
     * @return
     */
    public List<Summary> getSummary(Summary summary);

    /**
     * 添加 周小结
     * @param summary
     * @return
     */
    public String addSummary(Summary summary);


    /**
     * 修改 周小结
     * @param summary
     * @return
     */
    public String updateSummary(Summary summary);
    /**
     * 删除 周小结
     * @param summary
     * @return
     */
    public String deleteSummary(Summary summary);

}
