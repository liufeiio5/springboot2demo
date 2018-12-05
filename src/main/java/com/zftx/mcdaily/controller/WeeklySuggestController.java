package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.WeeklySuggest;
import com.zftx.mcdaily.service.WeeklySuggestService;
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
 * 周  建议
 */
@Controller
public class WeeklySuggestController {
    @Autowired
    private WeeklySuggestService weeklySuggestService;

    /**
     * 查询 周  建议
     * @param weeklySuggest
     * @return
     */
    @RequestMapping(value = "/getWeeklySuggest",method = RequestMethod.GET)
    @ResponseBody
    public R getWeeklySuggest(WeeklySuggest weeklySuggest){
        List<WeeklySuggest> list = weeklySuggestService.getWeeklySuggest(weeklySuggest);

        if(list !=null &&list.size()>0) {
            return R.ok("数据获取成功").put("data", list);
        }else {
            return R.error("数据获取失败");
        }
    }

    /**
     * 添加 周  建议
     * @param weeklySuggest
     * @return
     */
    @RequestMapping(value = "/addWeeklySuggest")
    @ResponseBody
    public R addWeeklySuggest(WeeklySuggest weeklySuggest){
        if(weeklySuggest!=null&&weeklySuggest.getSuggestId()!=null){
            String str=weeklySuggestService.addWeeklySuggest(weeklySuggest);
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
     * 修改 周  建议
     * @param weeklySuggest
     * @return
     */
    @RequestMapping("/updateWeeklySuggest")
    @ResponseBody
    public R updateWeeklySuggest(WeeklySuggest weeklySuggest,String sdate)throws ParseException{
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
        if(weeklySuggest!=null) {
            String str = weeklySuggestService.updateWeeklySuggest(weeklySuggest);
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
     * 删除  周  建议
     * @param weeklySuggest
     * @return
     */
    @RequestMapping( "/deleteWeeklySuggest")
    @ResponseBody
    public R deleteWeeklySuggest(WeeklySuggest weeklySuggest){
        if(weeklySuggest.getId()!=null||weeklySuggest.getSuggestId()!=0) {
            String str = weeklySuggestService.deleteWeeklySuggest(weeklySuggest);
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
