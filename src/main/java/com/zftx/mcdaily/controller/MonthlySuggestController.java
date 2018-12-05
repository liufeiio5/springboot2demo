package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.MonthlySuggest;
import com.zftx.mcdaily.service.MonthlySuggestService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MonthlySuggestController {

    @Autowired
    private MonthlySuggestService monthlySuggestService;


    @ResponseBody
    @RequestMapping(value = "/getmonthlySuggest",method = RequestMethod.GET)
    public R getmonthlySuggest(MonthlySuggest monthlySuggest){
        List<MonthlySuggest>list=monthlySuggestService.getmonthlySuggest(monthlySuggest);
        if(list.size()>0 && list!=null){
            return R.ok("获取数据成功").put("data",list);
        }else{
            return R.error("获取数据失败");
        }
    }

    @RequestMapping(value = "/addmonthlySuggest")
    @ResponseBody
    public R addmonthlySuggest(MonthlySuggest monthlySuggest){
        if(monthlySuggest!=null && monthlySuggest.getSuggestId()!=null){
            String result=monthlySuggestService.addmonthlySuggest(monthlySuggest);
            if("success".equals(result)){
                return R.ok("新增成功");
            }else{
                return R.error("新增失败");
            }
        }else{
            return R.error("新增失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delmonthlySuggest")
    public R delmonthlySuggest(MonthlySuggest monthlySuggest){
            if(monthlySuggest.getId()!=null && monthlySuggest.getId()!=null){
                String result=monthlySuggestService.delmonthlySuggest(monthlySuggest);
                if("success".equals(result)){
                    return R.ok("删除成功");
                }else{
                    return R.error("删除失败");
                }
            }else{
                return R.error("参数错误");
           }
    }

    @ResponseBody
    @RequestMapping(value = "/updatemonthlySuggest")
    public R updatemonthlySuggest(MonthlySuggest monthlySuggest){
        if(monthlySuggest!=null && monthlySuggest.getSuggestId()!=null){
            String result=monthlySuggestService.updatemonthlySuggest(monthlySuggest);
            if("success".equals(result)){
                return R.ok("修改成功");
            }else{
                return R.error("修改失败");
            }
        }else{
            return R.error("参数错误");
        }
    }
}
