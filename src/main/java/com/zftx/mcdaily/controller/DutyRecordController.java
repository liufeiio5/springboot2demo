package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.DutyRecord;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.DutyRecordService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/**
 * 值班记录
 */
@Controller
public class DutyRecordController {

    @Autowired
    private DutyRecordService dutyRecordService;

    @RequestMapping(value = "/dutyRecord")
    public String table(HttpSession session){
       if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "dutyRecord";
    }

    /**
     * 查询 值班记录
     * @param dutyRecord
     * @param userId
     * @param session
     * @return
     */
    @RequestMapping(value = "/getDutyRecord",method = RequestMethod.GET)
    @ResponseBody
    public R getDutyRecord(DutyRecord dutyRecord,Integer userId, HttpSession session)throws ParseException{
       //获取用户信息
        User user = (User) session.getAttribute("user");

        List<DutyRecord> list=dutyRecordService.getDutyRecord(dutyRecord,userId);

        if(list !=null &&list.size()>0) {
            return R.ok("数据获取成功").put("data", list).put("fullName", user != null ? user.getFullName() : "").put("userId",user.getId());
        }else {
            return R.error("数据获取失败").put("fullName", user != null ? user.getFullName() : "").put("userId",user.getId());
        }
    }

    /**
     * 添加 值班记录
     * @param dutyRecord
     * @return
     */
    @RequestMapping(value = "/addDutyRecord")
    @ResponseBody
    public R addWeekly(DutyRecord dutyRecord,HttpSession session,String empName){

        if(dutyRecord!=null) {
            if(empName!=null){
               dutyRecord.setEmpName(empName);
            }
            String str = dutyRecordService.addDutyRecord(dutyRecord);
            if ("success".equals(str)) {
                return R.ok("添加成功");
            }else if("repeat".equals(str)){
                return R.error("重复添加");
            }else{
                return R.ok("添加失败");
            }
        }else{
            return R.error("参数有误");
        }
    }


    /**
     * 修改 值班记录
     * @param dutyRecord
     * @param session
     * @return
     */
    @RequestMapping("/updateDutyRecord")
    @ResponseBody
    public R updateDutyRecord(DutyRecord dutyRecord,HttpSession session){
        if(dutyRecord!=null) {
            User user = (User) session.getAttribute("user");

            String str = dutyRecordService.updateDutyRecord(dutyRecord);
            if ("success".equals(str)) {
                return R.ok("修改成功");
            }else {
                return R.error("修改失败");
            }
        }else {
            return R.error("参数有误");
        }
    }



}
