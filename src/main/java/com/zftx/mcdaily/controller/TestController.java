package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.DailyRecord;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.DailyRecordService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private DailyRecordService dailyRecord;

    @RequestMapping("/show")
    @ResponseBody
    public String show() {
        return "hello world ";
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "login";
    }

    @RequestMapping(value = "/getUserId")
    @ResponseBody
    public R getUserId(HttpSession session){
        User user = (User)session.getAttribute("sessionUser");
        //System.out.println("用户："+user.getId());
        return R.ok().put("session.id",session.getId());
    }

    @RequestMapping(value = "/showDaily/{userId}")
    @ResponseBody
    public R someOneDaily(@PathVariable("userId") int userId){
        List<DailyRecord> dailyRecord = this.dailyRecord.getDailyRecord(new DailyRecord().setUserId(userId));
        if (dailyRecord!=null) {
            return R.ok().put("data",dailyRecord);
        }
        return R.error().put("msg","没取到数据");
    }

    @RequestMapping("thyme/index")
    public String thymeIndex(){
        return "html/index";
    }
}
