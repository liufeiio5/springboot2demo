package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.WeeklyDifficulty;
import com.zftx.mcdaily.service.WeeklyDifficultyService;
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
 * 周 困难
 */
@Controller
public class WeeklyDifficultyController {
    @Autowired
    private WeeklyDifficultyService weeklyDifficultyService;

    /**
     * 查询 周 困难
     * @param weeklyDifficulty
     * @return
     */
    @RequestMapping(value = "/getWeeklyDifficulty",method = RequestMethod.GET)
    @ResponseBody
    public R getWeekly(WeeklyDifficulty weeklyDifficulty){
        List<WeeklyDifficulty> list = weeklyDifficultyService.getWeeklyDifficulty(weeklyDifficulty);

        if(list !=null &&list.size()>0) {
            return R.ok("数据获取成功").put("data", list);
        }else {
            return R.error("数据获取失败");
        }
    }

    /**
     * 添加 周 困难
     * @param weeklyDifficulty
     * @return
     */
    @RequestMapping(value = "/addWeeklyDifficulty")
    @ResponseBody
    public R addWeekly(WeeklyDifficulty weeklyDifficulty){
        if(weeklyDifficulty!=null&&weeklyDifficulty.getDifficultyId()!=null){
            String str=weeklyDifficultyService.addWeeklyDifficulty(weeklyDifficulty);
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
     * 修改 周 困难
     * @param weeklyDifficulty
     * @return
     */
    @RequestMapping("/updateWeeklyDifficulty")
    @ResponseBody
    public R addDaily(WeeklyDifficulty weeklyDifficulty,String sdate)throws ParseException{
        if(sdate!=null && sdate!=""){
            String sevenDate=getSevenDate(sdate);
            Integer sdate2=Integer.parseInt(sdate);
            Integer nowDate2=Integer.parseInt(Tool.getNowDate());
            if(nowDate2>Integer.parseInt(sevenDate)){
                return R.error("当前时间不在此周内，禁止修改");
            }
        }
        if(weeklyDifficulty!=null) {
            String str = weeklyDifficultyService.updateWeeklyDifficulty(weeklyDifficulty);
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
     * 删除  周 困难
     * @param weeklyDifficulty
     * @return
     */
    @RequestMapping( "/deleteWeeklyDifficulty")
    @ResponseBody
    public R deleteWeekly(WeeklyDifficulty weeklyDifficulty){
        if(weeklyDifficulty.getId()!=null&&weeklyDifficulty.getId()!=0) {
            String str = weeklyDifficultyService.deleteWeeklyDifficulty(weeklyDifficulty);
            if ("success".equals(str)) {
                return R.ok("删除成功");
            } else {
                return R.error("删除失败");
            }
        }else{
            return R.error("参数有误!");
        }
    }

    //第6天后日期
    public String getSevenDate(String sdate)throws ParseException {
        String pattern = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date today = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, 1);
        Date today_plus1 = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, 1);
        Date today_plus2 = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, 1);
        Date today_plus3 = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, 1);
        Date today_plus4 = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, 1);
        Date today_plus5 = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, 1);
        Date today_plus6 = c.getTime();
        return  sdf.format(today_plus6);
    }
}
