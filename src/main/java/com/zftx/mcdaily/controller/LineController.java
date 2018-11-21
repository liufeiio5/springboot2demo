package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Line;
import com.zftx.mcdaily.service.LineService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LineController {

    @Autowired
    private LineService lineService;

    @RequestMapping(value = "/getLine")
    @ResponseBody
    public R getLine(Line line){
        List<Line> lines = lineService.findLineAll(line);
        if(lines.size()>0&&lines != null){
            return R.ok("数据获取成功").put("data",lines);
        }else{
            return R.error("获取数据失败");
        }
    }
}
