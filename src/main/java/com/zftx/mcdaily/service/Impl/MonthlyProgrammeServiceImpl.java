package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.MonthlyProgramme;
import com.zftx.mcdaily.mapper.MonthlyProgrammeMapper;
import com.zftx.mcdaily.service.MonthlyProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlyProgrammeServiceImpl implements MonthlyProgrammeService {

    @Autowired
    private MonthlyProgrammeMapper monthlyProgrammeMapper;

    @Override
    public List<MonthlyProgramme> getmonthlyProgramme(MonthlyProgramme monthlyProgramme) {
        return monthlyProgrammeMapper.getmonthlyProgramme(monthlyProgramme);
    }

    @Override
    public String addmonthlyProgramme(MonthlyProgramme monthlyProgramme) {

        Integer i=monthlyProgrammeMapper.addmonthlyProgramme(monthlyProgramme);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    @Override
    public String delmonthlyProgramme(MonthlyProgramme monthlyProgramme) {

        Integer i=monthlyProgrammeMapper.delmonthlyProgramme(monthlyProgramme);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

    @Override
    public String updatemonthlyProgramme(MonthlyProgramme monthlyProgramme) {

        Integer i=monthlyProgrammeMapper.updatemonthlyProgramme(monthlyProgramme);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}
