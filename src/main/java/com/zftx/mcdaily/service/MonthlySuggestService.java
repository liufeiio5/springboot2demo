package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.MonthlySuggest;

import java.util.List;

public interface MonthlySuggestService {

    /**
     * 查询所有建议
     *
     * @param monthlySuggest
     * @return
     */
    public List<MonthlySuggest> getmonthlySuggest(MonthlySuggest monthlySuggest);

    /**
     * 增加建议
     *
     * @param monthlySuggest
     * @return
     */
    public String addmonthlySuggest(MonthlySuggest monthlySuggest);

    /**
     * 删除建议
     *
     * @param monthlySuggest
     * @return
     */
    public String delmonthlySuggest(MonthlySuggest monthlySuggest);

    /**
     * 修改建议
     *
     * @param monthlySuggest
     * @return
     */
    public String updatemonthlySuggest(MonthlySuggest monthlySuggest);
    
}
