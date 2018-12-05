package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.MonthlySuggest;
import com.zftx.mcdaily.mapper.MonthlySuggestMapper;
import com.zftx.mcdaily.service.MonthlySuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MonthlySuggestServiceImpl implements MonthlySuggestService {

    @Autowired
    private MonthlySuggestMapper monthlySuggestMapper;

    @Override
    public List<MonthlySuggest> getmonthlySuggest(MonthlySuggest monthlySuggest) {
        return monthlySuggestMapper.getmonthlySuggest(monthlySuggest);
    }

    @Override
    public String addmonthlySuggest(MonthlySuggest monthlySuggest) {
        Integer i=monthlySuggestMapper.addmonthlySuggest(monthlySuggest);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    @Override
    public String delmonthlySuggest(MonthlySuggest monthlySuggest) {
        Integer i=monthlySuggestMapper.delmonthlySuggest(monthlySuggest);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    @Override
    public String updatemonthlySuggest(MonthlySuggest monthlySuggest) {
        Integer i=monthlySuggestMapper.updatemonthlySuggest(monthlySuggest);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}
