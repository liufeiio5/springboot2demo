package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Overtime;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.OvertimeService;
import com.zftx.mcdaily.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Slf4j
public class OvertimeController {

    @Autowired
    private OvertimeService overtimeService;

    /**
     * 添加加班记录
     * @param overtime
     * @return
     */
    @RequestMapping(value = "/addOvertimeRecord")
    @ResponseBody
    public R addOvertimeRecord(HttpSession session,Overtime overtime){
        User user = (User) session.getAttribute("user");
        overtime.setWorker(user.getId());
        SimpleDateFormat dateFormatDate = new SimpleDateFormat("yyyyMMdd");//格式化日期：20181130
        SimpleDateFormat dateFormatTime = new SimpleDateFormat("HH:mm");//格式化时间点：14：56
        overtime.setDate(dateFormatDate.format(new Date()))
                .setStartTime(dateFormatTime.format(new Date()));
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
