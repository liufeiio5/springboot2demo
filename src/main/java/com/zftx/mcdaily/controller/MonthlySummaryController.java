package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Monthly;
import com.zftx.mcdaily.bean.MonthlySummary;
import com.zftx.mcdaily.service.MonthlyService;
import com.zftx.mcdaily.service.MonthlySummaryService;
import com.zftx.mcdaily.util.R;
import com.zftx.mcdaily.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 月报
 */
@Controller
public class MonthlySummaryController {

    @Autowired
    private MonthlySummaryService monthlySummaryService;

    /**
     * 查询 月 小结
     * @param monthlySummary
     * @param session
     * @return
     */
    @RequestMapping(value = "/getMonthlySummary",method = RequestMethod.GET)
    @ResponseBody
    public R getMonthlySummary(MonthlySummary monthlySummary, HttpSession session){
        List<MonthlySummary> list =monthlySummaryService.getMonthlySummary(monthlySummary);
        if(list !=null &&list.size()>0) {
            return R.ok("数据获取成功").put("data", list);
        }else {
            return R.error("数据获取失败");
        }
    }


    /**
     * 添加 月小结
     * @param monthlySummary
     * @return
     */
    @RequestMapping(value = "/addMonthlySummary")
    @ResponseBody
    public R addMonthlySummary(MonthlySummary monthlySummary,String assismans,String year,String month)throws ParseException{
        if(monthlySummary!=null){
            if(year!=null&&month!=null) {
                if (Integer.parseInt(Tool.getNowDate()) > Integer.parseInt(Tool.getFutureDate(("" + year + Tool.getmm(month) + "31"), 3))) {
                    return R.error("此月月末三天后,禁止添加");
                }
            }
            monthlySummary.setAssisMan(assismans);
            String str=monthlySummaryService.addMonthlySummary(monthlySummary);
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
     * 修改 月小结
     * @param monthlySummary
     * @param session
     * @return
     */
    @RequestMapping("/updateMonthlySummary")
    @ResponseBody
    public R updateMonthlySummary(MonthlySummary monthlySummary,HttpSession session,String assismans,String year,String month)throws ParseException {
        if(monthlySummary!=null) {
            if(year!=null&&month!=null) {
                if (Integer.parseInt(Tool.getNowDate()) > Integer.parseInt(Tool.getFutureDate(("" + year + Tool.getmm(month) + "31"), 3))) {
                    return R.error("当前时间不在此月内,禁止修改");
                }
            }
            monthlySummary.setAssisMan(assismans);
            String str = monthlySummaryService.updateMonthlySummary(monthlySummary);
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
     * 删除  月 小结
     * @param monthlySummary
     * @return
     */
    @RequestMapping( "/deletMonthlySummary")
    @ResponseBody
    public R deleteMonthly(MonthlySummary monthlySummary){
        if(monthlySummary.getId()!=null&&monthlySummary.getId()!=0) {
            String str = monthlySummaryService.deleteMonthlySummary(monthlySummary);
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
