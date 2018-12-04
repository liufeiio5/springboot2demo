package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.*;
import com.zftx.mcdaily.service.*;
import com.zftx.mcdaily.util.R;
import com.zftx.mcdaily.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 周报
 */
@Controller
public class WeeklyController {

    @Autowired
    private WeeklyService weeklyService;

    @RequestMapping(value = "/weekly")
    public String table(HttpSession session){
        /*if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }*/
        return "weekly";
    }

    @RequestMapping(value = "/weeks")
    public String weeks(HttpSession session){
        /*if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }*/
        return "weeks";
    }

    /**
     * 查询 周报
     * @param weekly
     * @param session
     * @return
     */
    @RequestMapping(value = "/getWeekly",method = RequestMethod.GET)
    @ResponseBody
    public R getWeekly(Weekly weekly,HttpSession session){
        //获取用户信息
        User user = (User) session.getAttribute("user");
        if(user!=null && user.getId()!=null&&weekly.getUserId()==null){
            weekly.setUserId(user.getId());
        }
        if(weekly.getSdate().length()==6){
            weekly.setSdate(weekly.getSdate()+"00");
        }
        if(weekly.getEdate().length()==6){
            weekly.setEdate(weekly.getEdate()+"06");
        }
        ArrayList<HashMap<String, Object>> list = weeklyService.getWeekly(weekly);

        if(list != null && list.size() > 0)
            return R.ok("数据获取成功").put("data",list).put("fullName",user != null ? user.getFullName() : "").put("userId",weekly.getUserId());
        else {
            return R.error("数据获取失败").put("fullName", user != null ? user.getFullName() : "").put("userId",weekly.getUserId());
        }
    }

    /**
     * 添加 周报
     * @param weekly
     * @return
     */
    @RequestMapping(value = "/addWeekly")
    @ResponseBody
    public R addWeekly(Weekly weekly,HttpSession session){

        if(weekly!=null){
            //获取用户信息
            User user = (User) session.getAttribute("user");
            if(user!=null && user.getId()!=null&&weekly.getUserId()==null){
                weekly.setUserId(user.getId());
            }
            String str=weeklyService.addWeekly(weekly);
            if("success".equals(str)) {
                return R.ok("添加成功");
            }else{
                return R.ok("添加失败");
            }
        }else{
            return R.error("参数有误");
        }
    }

    /**
     * 修改 周报
     * @param weekly
     * @param session
     * @return
     */
    @RequestMapping("/updateWeekly")
    @ResponseBody
    public R addDaily(Weekly weekly,HttpSession session){
        if(weekly!=null) {
            //获取用户信息
            User user = (User) session.getAttribute("user");
            if(user!=null && user.getId()!=null&&weekly.getUserId()==null){
                weekly.setUserId(user.getId());
            }

            String str = weeklyService.updateWeekly(weekly);
            if ("success".equals(str)) {
                return R.ok("修改成功");
            } else {
                return R.error("修改失败");
            }
        }else {
            return R.error("参数有误");
        }
    }

    /**
     * 删除  日报
     * @param weekly
     * @return
     */
    @RequestMapping( "/deleteWeekly")
    @ResponseBody
    public R deleteWeekly(Weekly weekly){
        if(weekly.getId()!=null&&weekly.getId()!=0) {
            String str = weeklyService.deleteWeekly(weekly);
            if ("success".equals(str)) {
                return R.ok("删除成功");
            } else {
                return R.error("删除失败");
            }
        }else{
            return R.error("参数有误!");
        }
    }
}
