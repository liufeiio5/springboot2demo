package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Overtime;
import com.zftx.mcdaily.service.OvertimeService;
import com.zftx.mcdaily.util.R;
import com.zftx.mcdaily.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Controller
@Slf4j
public class OvertimeController {

    @Autowired
    private OvertimeService overtimeService;

    @RequestMapping(value = "/overtime")
    public String overtime(){
        return "overtime";
    }

    @ResponseBody
    @RequestMapping(value = "delOvertime")
    public R delOvertime(Overtime overtime){
        if(overtime.getId()!=null && overtime.getId()!=0){
            String result=overtimeService.delOvertime(overtime);
            if("success".equals(result)){
                return R.ok("删除成功!!");
            }else{
                return R.error("删除失败!!");
            }
        }else{
            return R.error("参数错误!!");
        }
    }

    /**
     * 添加加班记录
     * @param overtime
     * @return
     */
    @RequestMapping(value = "/addOvertimeRecord")
    @ResponseBody
    public R addOvertimeRecord(HttpSession session,Overtime overtime,String endtime){
        User user = (User) session.getAttribute("user");

        Date endTime=null;
        //字符串转换为Date类型，要做异常捕捉，转换有可能失败或者前端传过来的格式有错误
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
             endTime =  simpleDateFormat.parse(endtime);//格式转换

        }catch (ParseException e){
            e.printStackTrace();
        }

        overtime.setWorker(user.getId()).setEndTime(endTime);
        Integer result = overtimeService.addOverTimeRecord(overtime);
        if(result>0){
            log.info(this.getClass()+" || "+Thread.currentThread().getStackTrace()[1].getMethodName()+" ## message:添加成功"+" 参数："+overtime);
            return R.ok().put("data",overtime);
        }else {
            log.info(this.getClass()+" || "+Thread.currentThread().getStackTrace()[1].getMethodName()+" ## message:添加失败"+" 参数："+overtime);
            return R.error("添加失败");
        }
    }

}
