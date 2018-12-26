package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.TeaChoose;
import com.zftx.mcdaily.bean.TeaRepository;
import com.zftx.mcdaily.mapper.TeaChooseMapper;
import com.zftx.mcdaily.mapper.TeaRepositoryMapper;
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
    @Autowired
    private TeaRepositoryMapper teaRepositoryMapper;

    @Override
    public ArrayList<Map<String,Object>> getTeaChoose(TeaChoose teaChoose) {
        if(teaChoose.getDate()==null||teaChoose.getDate()=="") {
            teaChoose.setDate(Tool.getNowDate());
        }
        ArrayList<Map<String,Object>> list=teaChooseMapper.getTeaChoose(teaChoose);
        return list;
    }

    @Override
    public String addTeaChoose(TeaChoose teaChoose) {
        ArrayList<Map<String,Object>> trlist=teaRepositoryMapper.getTeaRepository(new TeaRepository().setId(teaChoose.getTeaId()),null,null,null);
        Float price=(Float)trlist.get(0).get("price");
        teaChoose.setMoney(teaChoose.getNumber()*price);
        if(teaChoose.getDate()==null||teaChoose.getDate()==""){
            teaChoose.setDate(Tool.getNowDate());
        }
        Integer allMopney = teaChooseMapper.isBeOutTenMoney(teaChoose);
        if(allMopney==null){
            allMopney=0;
        }
        if ((allMopney + teaChoose.getMoney()) > 10) {
            return "out";
        } else {
            ArrayList<Map<String, Object>> list = teaChooseMapper.getTeaChoose(new TeaChoose().setUserId(teaChoose.getUserId()).setTeaId(teaChoose.getTeaId()).setDate(teaChoose.getDate()));
            if (list.size() > 0) {
                return "repeat";
            } else {
                teaChoose.setDate(Tool.getNowDate());
                Integer i = teaChooseMapper.addTeaChoose(teaChoose);
                if (i > 0) {
                    return "success";
                }
                return "false";
            }
        }
    }

    @Override
    public String updateTeaChoose(TeaChoose teaChoose) {
        if(teaChoose.getDate()==null||teaChoose.getDate()==""){
            teaChoose.setDate(Tool.getNowDate());
        }
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
    public String isBeOutTenMoney(TeaChoose teaChoose,Float teaPrice) {
        if(teaChoose.getDate()==null||teaChoose.getDate()==""){
            teaChoose.setDate(Tool.getNowDate());
        }
        Integer allMopney=teaChooseMapper.isBeOutTenMoney(teaChoose);
        if(allMopney==null){
            allMopney=0;
        }

        if((allMopney+teaPrice)>10){
            return "out";
        }else{
            return ""+(allMopney+teaPrice);
        }
    }

    @Override
    public ArrayList<Map<String,Object>>  getTeaStatistics(TeaChoose teaChoose,String catName,String tName) {
        if(teaChoose.getDate()==null||teaChoose.getDate()==""){
            teaChoose.setDate(Tool.getNowDate());
        }
        ArrayList<Map<String,Object>> list=teaChooseMapper. getTeaStatistics(teaChoose,catName,tName);
        return list;
    }

    @Override
    public ArrayList<Map<String,Object>>  getTeaDistribute(TeaChoose teaChoose,String fullName) {
        if(teaChoose.getDate()==null||teaChoose.getDate()==""){
            teaChoose.setDate(Tool.getNowDate());
        }
        ArrayList<Map<String,Object>> list=teaChooseMapper.getTeaDistribute(teaChoose,fullName);
        return list;
    }

    @Override
    public ArrayList<Map<String,Object>>  getTeaUser(TeaChoose teaChoose,String fullName) {
        if(teaChoose.getDate()==null||teaChoose.getDate()==""){
            teaChoose.setDate(Tool.getNowDate());
        }
        ArrayList<Map<String,Object>> list=teaChooseMapper.getTeaUser(teaChoose,fullName);
        return list;
    }


    /**
     * 查询所有被选中的 品类（不重复）
     * @param teaChoose
     * @return
     */
    @Override
    public ArrayList<Map<String,Object>> getChooseTeaDistinctCatName(TeaChoose teaChoose){
        if(teaChoose.getDate()==null||teaChoose.getDate()==""){
            teaChoose.setDate(Tool.getNowDate());
        }
        ArrayList<Map<String,Object>> list=teaChooseMapper.getChooseTeaDistinctCatName(teaChoose);
        return list;
    }

    /**
     * 查询所有被选中的 茶点（不重复）
     * @param teaChoose
     * @param catName
     * @return
     */
    @Override
    public ArrayList<Map<String,Object>> getChooseTeaDistinctTName(TeaChoose teaChoose,String catName){
        if(teaChoose.getDate()==null||teaChoose.getDate()==""){
            teaChoose.setDate(Tool.getNowDate());
        }
        ArrayList<Map<String,Object>> list=teaChooseMapper.getChooseTeaDistinctTName(teaChoose,catName);
        return list;
    }
}
