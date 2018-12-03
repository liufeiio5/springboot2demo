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
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class OvertimeController {
    @Autowired
    OvertimeService overtimeService;

    @RequestMapping("/updateOvertime")
    @ResponseBody
    public R updateOvertime(Overtime overtime, HttpSession session){
        if(overtime!=null){
            String str=overtimeService.updateOvertime(overtime);
            if("success".equals(str)){
                return R.ok("修改成功");
            }else{
                return R.error("修改失败");
            }
        }else {
            return R.error("参数有误");
        }
    }

}
