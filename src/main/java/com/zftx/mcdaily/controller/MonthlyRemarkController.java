package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.MonthlyRemark;
import com.zftx.mcdaily.service.MonthlyRemarkService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 月报 备注
 */
@Controller
public class MonthlyRemarkController {

    @Autowired
    private MonthlyRemarkService monthlyRemarkService;

    /**
     * 查询 月 备注
     * @param monthlyRemark
     * @return
     */
    @RequestMapping(value = "/getMonthlyRemark",method = RequestMethod.GET)
    @ResponseBody
    public R getMonthlyRemark(MonthlyRemark monthlyRemark){
        List<MonthlyRemark> list =monthlyRemarkService.getMonthlyRemark(monthlyRemark);
        if(list !=null &&list.size()>0) {
            return R.ok("数据获取成功").put("data", list);
        }else {
            return R.error("数据获取失败");
        }
    }


    /**
     * 添加 月 备注
     * @param monthlyRemark
     * @return
     */
    @RequestMapping(value = "/addMonthlyRemark")
    @ResponseBody
    public R addMonthlyRemark(MonthlyRemark monthlyRemark){
        if(monthlyRemark!=null){
            String str=monthlyRemarkService.addMonthlyRemark(monthlyRemark);
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
     * 修改 月 备注
     * @param monthlyRemark
     * @return
     */
    @RequestMapping("/updateMonthlyRemark")
    @ResponseBody
    public R updateMonthlyRemark(MonthlyRemark monthlyRemark){
        if(monthlyRemark!=null) {
            String str = monthlyRemarkService.updateMonthlyRemark(monthlyRemark);
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
     * 删除  月 备注
     * @param monthlyRemark
     * @return
     */
    @RequestMapping( "/deleteMonthlyRemark")
    @ResponseBody
    public R deleteMonthlyRemark(MonthlyRemark monthlyRemark){
        if(monthlyRemark.getId()!=null&&monthlyRemark.getId()!=0) {
            String str = monthlyRemarkService.deleteMonthlyRemark(monthlyRemark);
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
