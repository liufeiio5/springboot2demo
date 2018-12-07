package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.WeeklyProgramme;
import com.zftx.mcdaily.service.WeeklyProgrammeService;
import com.zftx.mcdaily.util.R;
import com.zftx.mcdaily.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 周  解决方案
 */
@Controller
public class WeeklyProgrammeController {
    @Autowired
    private WeeklyProgrammeService weeklyProgrammeService;

    /**
     * 查询 周 解决方案
     * @param weeklyProgramme
     * @return
     */
    @RequestMapping(value = "/getWeeklyProgramme",method = RequestMethod.GET)
    @ResponseBody
    public R getWeeklyProgramme(WeeklyProgramme weeklyProgramme){
        List<WeeklyProgramme> list = weeklyProgrammeService.getWeeklyProgramme(weeklyProgramme);

        if(list !=null &&list.size()>0) {
            return R.ok("数据获取成功").put("data", list);
        }else {
            return R.error("数据获取失败");
        }
    }

    /**
     * 添加 周  解决方案
     * @param weeklyProgramme
     * @param sdate
     * @return
     */
    @RequestMapping(value = "/addWeeklyProgramme")
    @ResponseBody
    public R addWeeklyProgramme(WeeklyProgramme weeklyProgramme,String sdate)throws ParseException{
        if(weeklyProgramme!=null&&weeklyProgramme.getProgrammeId()!=null){
            if(Integer.parseInt(Tool.getNowDate())>Integer.parseInt(Tool.getFutureDate(sdate,6))){
                return R.error("当前时间不在此周内,禁止添加");
            }
            String str=weeklyProgrammeService.addWeeklyProgramme(weeklyProgramme);
            if("success".equals(str)) {
                return R.ok("添加成功");
            }else{
                return R.error("添加失败");
            }
        }else{
            return R.error("参数有误");
        }
    }

    /**
     * 修改 周  解决方案
     * @param weeklyProgramme
     * @param sdate
     * @return
     */
    @RequestMapping("/updateWeeklyProgramme")
    @ResponseBody
    public R updateWeeklyProgramme(WeeklyProgramme weeklyProgramme,String sdate)throws ParseException{
        if(sdate!=null && sdate!=""){
            if(Integer.parseInt(Tool.getNowDate())>Integer.parseInt(Tool.getFutureDate(sdate,6))){
                return R.error("当前时间不在此周内，禁止修改");
            }
        }
        if(weeklyProgramme!=null) {
            String str = weeklyProgrammeService.updateWeeklyProgramme(weeklyProgramme);
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
     * 删除  周  解决方案
     * @param weeklyProgramme
     * @return
     */
    @RequestMapping( "/deleteWeeklyProgramme")
    @ResponseBody
    public R deleteWeeklyProgramme(WeeklyProgramme weeklyProgramme){
        if(weeklyProgramme.getId()!=null&&weeklyProgramme.getId()!=0) {
            String str = weeklyProgrammeService.deleteWeeklyProgramme(weeklyProgramme);
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
