package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.MonthlySuggest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlySuggestMapper {

    /**
     * 查询所有建议
     *
     * @param monthlySuggest
     * @return
     */
    public List<MonthlySuggest> getmonthlySuggest(@Param("monthlySuggest") MonthlySuggest monthlySuggest);

    /**
     * 增加建议
     *
     * @param monthlySuggest
     * @return
     */
    public Integer addmonthlySuggest(@Param("monthlySuggest") MonthlySuggest monthlySuggest);

    /**
     * 删除建议
     *
     * @param monthlySuggest
     * @return
     */
    public Integer delmonthlySuggest(@Param("monthlySuggest") MonthlySuggest monthlySuggest);

    /**
     * 修改建议
     *
     * @param monthlySuggest
     * @return
     */
    public Integer updatemonthlySuggest(@Param("monthlySuggest") MonthlySuggest monthlySuggest);


}
