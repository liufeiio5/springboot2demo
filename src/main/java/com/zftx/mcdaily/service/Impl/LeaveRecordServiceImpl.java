package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.LeaveRecord;
import com.zftx.mcdaily.mapper.LeaveRecordMapper;
import com.zftx.mcdaily.mapper.UserMapper;
import com.zftx.mcdaily.service.LeaveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class LeaveRecordServiceImpl implements LeaveRecordService {
    @Autowired
    private LeaveRecordMapper leaveRecordMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public String addLeaveRecord(LeaveRecord leaveRecord) {
        ArrayList<Map<String,Object>> list=leaveRecordMapper.getLeaveRecord(new LeaveRecord().setId(leaveRecord.getId()).setLeaveName(leaveRecord.getLeaveName()),null);
        if(list.size()>0){
            return "repeat";
        }else{
            //先添加
            Integer i=leaveRecordMapper.addLeaveRecord(leaveRecord);
            if(i>0){
                return "success";
            }else{
                return "fails";
            }
        }
    }

    @Override
    public  ArrayList<Map<String,Object>> getLeaveRecord(LeaveRecord leaveRecord,String fullName) {
        ArrayList<Map<String,Object>>  list = leaveRecordMapper.getLeaveRecord(leaveRecord,fullName);
        if(list.size()>0 && list!=null){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public String updateLeaveRecord(LeaveRecord leaveRecord,Integer leaveFlag) {
        ArrayList<Map<String,Object>> list=leaveRecordMapper.getLeaveRecord(new LeaveRecord().setId(leaveRecord.getId()),null);
        Integer i=0;
        if(list.size()>1){
        System.out.println("ppppppppppppppppppppppppppppp"+leaveFlag);
            i=leaveRecordMapper.updateLeaveRecord(leaveRecord,leaveFlag);
        }else{
        System.out.println("ssssssssssssssssssssssssssssss"+leaveRecord);
            i = leaveRecordMapper.updateLeaveRecord(leaveRecord.setLeaveName(leaveFlag),null);
        }
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }
}
