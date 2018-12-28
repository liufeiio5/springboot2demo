package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.WeeklyRemark;
import com.zftx.mcdaily.service.WeeklyRemarkService;
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
 * 周报 备注
 */
@Controller
public class WeeklyRemarkController {
    @Autowired
    private WeeklyRemarkService weeklyRemarkService;

    /**
     * 查询 周 备注
     *
     * @param weeklyRemark
     * @return
     */
    @RequestMapping(value = "/getWeeklyRemark", method = RequestMethod.GET)
    @ResponseBody
    public R getWeeklyRemark(WeeklyRemark weeklyRemark) {
        List<WeeklyRemark> list = weeklyRemarkService.getWeeklyRemark(weeklyRemark);

        if (list != null && list.size() > 0) {
            return R.ok("数据获取成功").put("data", list);
        } else {
            return R.error("数据获取失败");
        }
    }

    /**
     * 添加 周  备注
     *
     * @param weeklyRemark
     * @return
     */
    @RequestMapping(value = "/addWeeklyRemark")
    @ResponseBody
    public R addWeeklyRemark(WeeklyRemark weeklyRemark)throws ParseException{
        if (weeklyRemark != null && weeklyRemark.getRemarkId() != null) {
            String str = weeklyRemarkService.addWeeklyRemark(weeklyRemark);
            if ("success".equals(str)) {
                return R.ok("添加成功");
            } else {
                return R.ok("添加失败");
            }
        } else {
            return R.error("参数有误");
        }
    }

    /**
     * 修改 周  备注
     *
     * @param weeklyRemark
     * @return
     */
    @RequestMapping("/updateWeeklyRemark")
    @ResponseBody
    public R updateWeeklyRemark(WeeklyRemark weeklyRemark) throws ParseException {
        if (weeklyRemark != null) {
            String str = weeklyRemarkService.updateWeeklyRemark(weeklyRemark);
            if ("success".equals(str)) {
                return R.ok("修改成功");
            } else {
                return R.error("修改失败");
            }
        } else {
            return R.error("参数有误");
        }
    }

    /**
     * 删除  周  备注
     *
     * @param weeklyRemark
     * @return
     */
    @RequestMapping("/deleteWeeklyRemark")
    @ResponseBody
    public R deleteWeeklyRemark(WeeklyRemark weeklyRemark) {
        if (weeklyRemark.getId() != null || weeklyRemark.getRemarkId() != 0) {
            String str = weeklyRemarkService.deleteWeeklyRemark(weeklyRemark);
            if ("success".equals(str)) {
                return R.ok("删除成功");
            } else {
                return R.error("删除失败");
            }
        } else {
            return R.error("参数有误!");
        }
    }
}
