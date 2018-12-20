package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.LeaveRecord;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.LeaveRecordService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class LeaveRecordController {

    @Autowired
    private LeaveRecordService LeaveRecordService;

    @RequestMapping(value = "/leaveRecord")
    public String table(HttpSession session){
        if (session.getAttribute("sessionUser") == null) {
            return "redirect:/login";
        }
        return "leaveRecord";
    }

    @RequestMapping(value = "/getLeaveRecord",method = RequestMethod.GET)
    @ResponseBody
    public R getLeaveRecord(HttpSession session, LeaveRecord leaveRecord,String fullName){
        User user= (User) session.getAttribute("sessionUser");
        ArrayList<Map<String,Object>> list=LeaveRecordService.getLeaveRecord(leaveRecord,fullName);

        if(list!=null && list.size()>0){
            return R.ok("获取数据成功！！").put("data",list);
        }else{
            return R.error("获取数据失败！！");
        }

    }

    @ResponseBody
    @RequestMapping( "/addLeaveRecord")
    public R addLeaveRecord(LeaveRecord leaveRecord){

        if(leaveRecord!=null) {
            String str = LeaveRecordService.addLeaveRecord(leaveRecord);
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

    @RequestMapping("/updateLeaveRecord")
    @ResponseBody
    public R updateLeaveRecord(LeaveRecord leaveRecord,Integer leaveFlag,HttpSession session){
        if(leaveRecord!=null) {
            String str = LeaveRecordService.updateLeaveRecord(leaveRecord,leaveFlag);
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
