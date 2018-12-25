package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.TeaRepository;
import com.zftx.mcdaily.mapper.TeaRepositoryMapper;
import com.zftx.mcdaily.service.TeaRepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TeaRepositoryServiceImpl implements TeaRepositoryService {

    @Autowired
    private TeaRepositoryMapper teaRepositoryMapper;

    @Override
    public ArrayList<Map<String,Object>> getTeaRepository(TeaRepository teaRepository,String flag,Float lowPrice,Float highPrice) {
        ArrayList<Map<String,Object>> list=teaRepositoryMapper.getTeaRepository(teaRepository,flag,lowPrice,highPrice);
        return list;
    }

    @Override
    public String addTeaRepository(TeaRepository teaRepository) {
        Integer i = teaRepositoryMapper.addTeaRepository(teaRepository);
        if (i> 0) {
            return "success";
        }
        return "false";
    }

    @Override
    public String updateTeaRepository(TeaRepository teaRepository) {
        Integer i = teaRepositoryMapper.updateTeaRepository(teaRepository);
        if (i> 0) {
            return "success";
        }
        return "false";
    }

    @Override
    public String delTeaRepository(TeaRepository teaRepository) {
            Integer i = teaRepositoryMapper.delTeaRepository(teaRepository);
            if (i> 0) {
                return "success";
            }
            return "false";
    }

    @Override
    public List<TeaRepository> getTeaRepositoryCatName() {
        List<TeaRepository> catNameList=teaRepositoryMapper.getTeaRepositoryCatName();
        return catNameList;
    }
}
