package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.MonthlyDifficulty;
import com.zftx.mcdaily.service.MonthlyDifficultyService;
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
 * 月报
 */
@Controller
public class MonthlyDifficultyController {

    @Autowired
    private MonthlyDifficultyService monthlyDifficultyService;

    /**
     * 查询 月 困难
     * @param monthlyDifficulty
     * @return
     */
    @RequestMapping(value = "/getMonthlyDifficulty",method = RequestMethod.GET)
    @ResponseBody
    public R getMonthlyDifficulty(MonthlyDifficulty monthlyDifficulty){
        List<MonthlyDifficulty> list =monthlyDifficultyService.getMonthlyDifficulty(monthlyDifficulty);
        if(list !=null &&list.size()>0) {
            return R.ok("数据获取成功").put("data", list);
        }else {
            return R.error("数据获取失败");
        }
    }


    /**
     * 添加 月 困难
     * @param monthlyDifficulty
     * @return
     */
    @RequestMapping(value = "/addMonthlyDifficulty")
    @ResponseBody
    public R addMonthlyDifficulty(MonthlyDifficulty monthlyDifficulty){
        if(monthlyDifficulty!=null){
            String str=monthlyDifficultyService.addMonthlyDifficulty(monthlyDifficulty);
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
     * 修改 月 困难
     * @param monthlyDifficulty
     * @return
     */
    @RequestMapping("/updateMonthlyDifficulty")
    @ResponseBody
    public R updateMonthlyDifficulty(MonthlyDifficulty monthlyDifficulty,String year,String month)throws ParseException{
        if(monthlyDifficulty!=null) {
            if(Integer.parseInt(Tool.getNowDate())>Integer.parseInt(Tool.getFutureDate((""+year+Tool.getmm(month)+"31"),3))){
                return R.error("当前时间不在此月内,禁止修改");
            }
            String str = monthlyDifficultyService.updateMonthlyDifficulty(monthlyDifficulty);
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
     * 删除  月 困难
     * @param monthlyDifficulty
     * @return
     */
    @RequestMapping( "/deletMonthlyDifficulty")
    @ResponseBody
    public R deleteMonthlyDifficulty(MonthlyDifficulty monthlyDifficulty){
        if(monthlyDifficulty.getId()!=null&&monthlyDifficulty.getId()!=0) {
            String str = monthlyDifficultyService.deleteMonthlyDifficulty(monthlyDifficulty);
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
