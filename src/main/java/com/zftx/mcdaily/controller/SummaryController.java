package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Summary;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.SummaryService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/**
 * 周小结
 */
@Controller
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

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
     * @param assismans
     * @return
     */
    @RequestMapping(value = "/addSummary")
    @ResponseBody
    public R addSummary(Summary summary,String assismans)throws ParseException{
        if(summary!=null){
            summary.setAssisMan(assismans);
            String str=summaryService.addSummary(summary);
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
     * 修改 周小结
     * @param summary
     * @param session
     * @param assismans
     * @return
     */
    @RequestMapping("/updateSummary")
    @ResponseBody
    public R updateSummary(Summary summary,HttpSession session,String assismans)throws ParseException{
        if(summary!=null) {
            summary.setAssisMan(assismans);
            //获取用户信息
            User user = (User) session.getAttribute("sessionUser");

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
}
