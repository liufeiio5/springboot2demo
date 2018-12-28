package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.MonthlyProgramme;
import com.zftx.mcdaily.service.MonthlyProgrammeService;
import com.zftx.mcdaily.util.R;
import com.zftx.mcdaily.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

@Controller
public class MonthlyProgrammeController {

    @Autowired
    private MonthlyProgrammeService monthlyProgrammeService;


    @ResponseBody
    @RequestMapping(value = "/getMonthlyProgramme",method = RequestMethod.GET)
    public R getMonthlyProgramme(MonthlyProgramme monthlyProgramme){
        List<MonthlyProgramme>list=monthlyProgrammeService.getmonthlyProgramme(monthlyProgramme);
        if(list.size()>0 && list!=null){
            return R.ok("获取数据成功").put("data",list);
        }else{
            return R.error("获取数据失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addmonthlyProgramme")
    public R addmonthlyProgramme(MonthlyProgramme monthlyProgramme)throws ParseException{
        if(monthlyProgramme!=null && monthlyProgramme.getProgrammeId()!=null){
            String result=monthlyProgrammeService.addmonthlyProgramme(monthlyProgramme);
            if("success".equals(result)){
                return R.ok("添加成功");
            }else{
                return R.error("添加失败");
            }
        }else{
            return R.error("参数错误！");
        }
    }

    /**
     * 删除 月 方案
     * @param monthlyProgramme
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delmonthlyProgramme")
    public R delmonthlyProgramme(MonthlyProgramme monthlyProgramme){
        if(monthlyProgramme.getId()!=null && monthlyProgramme.getId()!=null){
            String result=monthlyProgrammeService.delmonthlyProgramme(monthlyProgramme);
            if("success".equals(result)){
                return R.ok("删除成功");
            }else{
                return R.error("删除失败");
            }
        }else{
            return R.ok("参数错误！");
        }
    }

    /**
     * 修改 月 方案
     * @param monthlyProgramme
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatemonthlyProgramme")
    public R updatemonthlyProgramme(MonthlyProgramme monthlyProgramme)throws ParseException {
        if(monthlyProgramme!=null && monthlyProgramme.getProgrammeId()!=null){
            String result=monthlyProgrammeService.updatemonthlyProgramme(monthlyProgramme);
            if("success".equals(result)){
                return R.ok("修改成功");
            }else{
                return R.error("修改失败");
            }
        }else{
            return R.ok("参数错误！");
        }
    }
}
