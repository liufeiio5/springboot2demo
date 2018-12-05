package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.WeeklySuggest;
import com.zftx.mcdaily.mapper.WeeklySuggestMapper;
import com.zftx.mcdaily.service.WeeklySuggestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WeeklySuggestServiceImpl implements WeeklySuggestService {

    @Autowired
    private WeeklySuggestMapper weeklySuggestMapper;

    /**
     * 查询 周报  困难
     * @param weeklySuggest
     * @return
     */
    public List<WeeklySuggest> getWeeklySuggest(@Param("weeklySuggest") WeeklySuggest weeklySuggest){
        //日志
        StringBuilder info=new StringBuilder().append(this.getClass().getName()).append("||").
                append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&参数：Weekly{},");
        log.info(info.toString(),weeklySuggest.toString());
        return weeklySuggestMapper.getWeeklySuggest(weeklySuggest);
    }

    /**
     * 添加 周报  困难
     * @param weeklySuggest
     * @return
     */
    public String addWeeklySuggest(@Param("weeklySuggest") WeeklySuggest weeklySuggest){
        int i=weeklySuggestMapper.addWeeklySuggest(weeklySuggest);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 修改 周报  困难
     * @param weeklySuggest
     * @return
     */
    public String updateWeeklySuggest(@Param("weeklySuggest") WeeklySuggest weeklySuggest){
        int i=weeklySuggestMapper.updateWeeklySuggest(weeklySuggest);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    /**
     * 删除 周报  困难
     * @param weeklySuggest
     * @return
     */
    public String deleteWeeklySuggest(WeeklySuggest weeklySuggest){
        int i=weeklySuggestMapper.deleteWeeklySuggest(weeklySuggest);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}
