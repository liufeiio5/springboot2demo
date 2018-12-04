package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Summary;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.bean.Weekly;
import com.zftx.mcdaily.service.SummaryService;
import com.zftx.mcdaily.service.WeeklyService;
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
 * 周小结
 */
@Controller
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    @RequestMapping(value = "/summary")
    public String table(HttpSession session)
    {
        /*if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }*/
        return "summary";
    }

    /**
     * 查询 周小结
     * @param summary
     * @return
     */
    @RequestMapping("/getSummary")
    @ResponseBody
    public R getSummary(Summary summary){
        List<Summary> list = summaryService.getSummary(summary);
        if(list !=null &&list.size()>0)
            return R.ok("数据获取成功").put("data",list);
        else
            return R.error("数据获取失败");
    }

    /**
     * 添加 周小结
     * @param summary
     * @return
     */
    @RequestMapping(value = "/addSummary")
    @ResponseBody
    public R addSummary(Summary summary,String assismans){
        if(summary!=null){
            summary.setAssisMan(assismans);
            String str=summaryService.addSummary(summary);
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
     * 修改 周小结
     * @param summary
     * @param session
     * @return
     */
    @RequestMapping("/updateSummary")
    @ResponseBody
    public R updateSummary(Summary summary,HttpSession session,String sdate)throws ParseException{
        if(sdate!=null && sdate!=""){
            String sevenDate=getSevenDate(sdate);
            //获取当前日期
            String mouth= Tool.getMonth()< 10 ? "0" +Tool.getMonth() : Tool.getMonth()+"";
            String day= Tool.getToday() < 10 ? "0" +Tool.getToday() : Tool.getToday()+"";
            String nowDate=Tool.getYear()+""+mouth+day;
            Integer nowDate2=Integer.parseInt(nowDate);
            if(nowDate2>Integer.parseInt(sevenDate)){
                return R.error("当前时间不在此周内，禁止修改");
            }
        }
        if(summary!=null) {
            //获取用户信息
            User user = (User) session.getAttribute("user");

            String str = summaryService.updateSummary(summary);
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
     * 删除 周小结
     * @param summary
     * @return
     */
    @RequestMapping( "/deleteSummary")
    @ResponseBody
    public R deleteSummary(Summary summary){
        if(summary.getId()!=null&&summary.getId()!=0) {
            String str = summaryService.deleteSummary(summary);
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
