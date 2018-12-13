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
import java.text.ParseException;
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
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "weekly";
    }

    /**
     * 查询 周报
     * @param weekly
     * @param session
     * @return
     */
    @RequestMapping(value = "/getWeekly",method = RequestMethod.GET)
    @ResponseBody
    public R getWeekly(Weekly weekly,HttpSession session,String year,String mouth)throws ParseException{
        if(weekly.getUserId()==null||weekly.getUserId()==0){
            User user=(User) session.getAttribute("sessionUser");
            weekly.setUserId(user.getId());
        }
        //限制为近一月的周报
        if(weekly.getSdate()==null&&weekly.getEdate()==null){
            weekly.setSdate(Tool.getFutureDate(Tool.getNowDate(),-31));
            weekly.setEdate(Tool.getNowDate());
        }
        if(year!=null&&mouth!=null){
            if(year!="" && mouth!=""){
                weekly.setSdate(year+""+mouth+"00");
                weekly.setEdate(Tool.getFutureDate((year+""+mouth+"31"),4));
            }else if(year!=""&& mouth==""){
                weekly.setSdate(year+"0100");
                weekly.setEdate(year+"1231");
            }
        }
        ArrayList<HashMap<String, Object>> list = weeklyService.getWeekly(weekly);

        if(list !=null &&list.size()>0) {
            return R.ok("数据获取成功").put("data", list);
        }else {
            return R.error("数据获取失败");
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

        if(weekly!=null) {
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");//格式化日期
            //不能提前插入之后的周报
            if (weekly.getEdate() != null && weekly.getEdate() != "") {
                if (Integer.parseInt(weekly.getEdate()) > Integer.parseInt(dateFormat1.format(new Date()))) {
                    return R.error("不能提前创建周报");
                }
            }
            String str = weeklyService.addWeekly(weekly);
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
     * 删除  周报
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
