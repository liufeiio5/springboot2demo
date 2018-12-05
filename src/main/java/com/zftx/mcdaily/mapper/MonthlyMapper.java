package com.zftx.mcdaily.mapper;

import com.zftx.mcdaily.bean.Monthly;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public interface MonthlyMapper {
        /**
         * 查询月报
         * @param monthly
         * @return
         */
        public ArrayList<HashMap<String, Object>> getMonthly(@Param("monthly") Monthly monthly);


        /**
         * 删除周报
         * @param monthly
         * @return
         */
        public Integer deleteMonthly(@Param("monthly") Monthly monthly);


}
