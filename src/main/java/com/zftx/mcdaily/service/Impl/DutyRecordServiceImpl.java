package com.zftx.mcdaily.service.Impl;

import com.zftx.mcdaily.bean.DutyRecord;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.mapper.DutyRecordMapper;
import com.zftx.mcdaily.mapper.UserMapper;
import com.zftx.mcdaily.service.DutyRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DutyRecordServiceImpl implements DutyRecordService {

    @Autowired
    private DutyRecordMapper dutyRecordMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询 值班记录
     * @param dutyRecord
     * @return
     */
    public List<DutyRecord> getDutyRecord(@Param("dutyRecord") DutyRecord dutyRecord,Integer userId){
        List<DutyRecord> list;
        if(userId!=null){
            List<User> ulist=userMapper.getUser(new User().setId(userId));
            if(ulist.size()>0){
                list=dutyRecordMapper.getDutyRecord(dutyRecord.setEmpName(ulist.get(0).getFullName()));
            }else{
                return null;
            }
        }else {
            list = dutyRecordMapper.getDutyRecord(dutyRecord);
        }
        if(list.size()>0){
            return list;
        }else{
            return null;
        }
    }

    /**
     * 添加 值班记录
     * @param dutyRecord
     * @return
     */
    public String addDutyRecord(@Param("dutyRecord") DutyRecord dutyRecord){
        List<DutyRecord> list=dutyRecordMapper.getDutyRecord(new DutyRecord().setId(dutyRecord.getId()));
        if(list.size()>0){
            return "repeat";
        }else{
            //先添加
            Integer i=dutyRecordMapper.addDutyRecord(dutyRecord);
            if(i>0){
                return "success";
            }else{
                return "fails";
            }
        }
    }


    /**
     * 修改 值班记录
     * @param dutyRecord
     * @return
     */
    public String updateDutyRecord(@Param("dutyRecord") DutyRecord dutyRecord){
        Integer i=dutyRecordMapper.updateDutyRecord(dutyRecord);
        if(i>0){
            return "success";
        }else{
            return "fails";
        }
    }

}
