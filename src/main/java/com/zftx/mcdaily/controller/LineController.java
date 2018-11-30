package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Line;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.LineService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LineController {

    @Autowired
    private LineService lineService;

    @RequestMapping(value = "/getLine")
    @ResponseBody
    public R getLine(HttpSession session,Line line){
        User user = (User) session.getAttribute("user");
        List<Line> lines = lineService.findLineAll(line.setCreateUser(user.getId()));
        if(lines.size()>0&&lines != null){
            return R.ok("数据获取成功").put("data",lines);
        }else{
            return R.error("获取数据失败");
        }
    }

    /**
     * 添加线
     * @param line
     * @return
     */
    @RequestMapping(value = "/addLine",method = RequestMethod.POST)
    @ResponseBody
    public R addLine(HttpSession session,Line line){
        User user = (User) session.getAttribute("user");
        line.setCreateUser(user.getId());
        Integer result = lineService.addLine(line);
        if(result>0){
            return R.ok("添加成功").put("result",result);
        }else{
            return R.error("添加失败");
        }
    }

    /**
     * 修改线
     * @param line
     * @return
     */
    @RequestMapping(value = "/updateLine",method = RequestMethod.PUT)
    @ResponseBody
    public R updateLine(HttpSession session,Line line){
        User user = (User) session.getAttribute("user");
        line.setCreateUser(user.getId());
        Integer result = lineService.updateLine(line);
        if(result>0){
            return R.ok("添加成功").put("result",result);
        }else{
            return R.error("添加失败");
        }
    }
}
