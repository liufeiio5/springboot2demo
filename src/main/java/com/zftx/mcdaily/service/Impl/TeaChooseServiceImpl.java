package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.TeaChoose;
import com.zftx.mcdaily.mapper.TeaChooseMapper;
import com.zftx.mcdaily.service.TeaChooseService;
import com.zftx.mcdaily.util.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
@Slf4j
public class TeaChooseServiceImpl implements TeaChooseService {

    @Autowired
    private TeaChooseMapper teaChooseMapper;

    @Override
    public ArrayList<Map<String,Object>> getTeaChoose(TeaChoose teaChoose) {
        teaChoose.setDate(Tool.getNowDate());
        ArrayList<Map<String,Object>> list=teaChooseMapper.getTeaChoose(teaChoose);
        return list;
    }

    @Override
    public String addTeaChoose(TeaChoose teaChoose){
        ArrayList<Map<String,Object>> list=teaChooseMapper.getTeaChoose(teaChoose);
        if(list.size()>0) {
            return "repeat";
        }else{
            teaChoose.setDate(Tool.getNowDate());
            Integer i = teaChooseMapper.addTeaChoose(teaChoose);
            if (i > 0) {
                return "success";
            }
            return "false";
        }
    }

    @Override
    public String updateTeaChoose(TeaChoose teaChoose) {
        Integer i = teaChooseMapper.updateTeaChoose(teaChoose);
        if (i> 0) {
            return "success";
        }
        return "false";
    }

    @Override
    public String delTeaChoose(TeaChoose teaChoose) {
        Integer i = teaChooseMapper.delTeaChoose(teaChoose);
        if (i> 0) {
            return "success";
        }
        return "false";
    }


    @Override
    public String isBeOutTenMoney(TeaChoose teaChoose,Integer teaPrice) {
        Integer allMopney=teaChooseMapper.isBeOutTenMoney(teaChoose);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx"+allMopney);
        if((allMopney+teaPrice)>10){
            return "out";
        }else{
            return ""+(allMopney+teaPrice);
        }
    }
}
