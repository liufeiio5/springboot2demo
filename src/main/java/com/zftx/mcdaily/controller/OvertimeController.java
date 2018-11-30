package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Overtime;
import com.zftx.mcdaily.service.OvertimeService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OvertimeController {

    @Autowired
    private OvertimeService overtimeService;

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
}
