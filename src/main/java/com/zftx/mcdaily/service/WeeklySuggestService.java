package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.WeeklySuggest;

import java.util.List;

public interface WeeklySuggestService {

    /**
     * 查询 周报 困难
     * @param weeklySuggest
     * @return
     */
    public List<WeeklySuggest> getWeeklySuggest(WeeklySuggest weeklySuggest);

    /**
     * 添加 周报 困难
     * @param weeklySuggest
     * @return
     */
    public String addWeeklySuggest(WeeklySuggest weeklySuggest);


    /**
     * 修改 周报 困难
     * @param weeklySuggest
     * @return
     */
    public String updateWeeklySuggest(WeeklySuggest weeklySuggest);
    /**
     * 删除 周报 困难
     * @param weeklySuggest
     * @return
     */
    public String deleteWeeklySuggest(WeeklySuggest weeklySuggest);

}
