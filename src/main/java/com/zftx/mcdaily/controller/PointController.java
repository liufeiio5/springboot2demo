package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Point;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.PointService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PointController {

    @Autowired
    private PointService pointService;

    @RequestMapping(value = "/getPoint",method = RequestMethod.GET)
    @ResponseBody
    public R getPoint(HttpSession session,Point point){
        User user = (User) session.getAttribute("user");
        point.setCreateUser(user.getId());
        System.out.println("用户ID"+user.getId());
        List<Point> pointList = pointService.findPointAll(point);
        System.out.println("点数据=========================："+pointList);
        if(pointList.size()>0 && pointList != null){
            return R.ok("数据获取成功").put("data",pointList);
        }else {
            return R.error("获取数据失败");
        }
    }

    /**
     * 添加点
     * @param point
     * @return
     */
    @RequestMapping(value = "/addPoint",method = RequestMethod.POST)
    @ResponseBody
    public R addPoint(HttpSession session,Point point){
        User user = (User) session.getAttribute("user");
        point.setCreateUser(user.getId());
        Integer result = pointService.addPoint(point);
        if(result>0){
            return R.ok("添加成功").put("result",result);
        }else{
            return R.error("添加失败");
        }
    }

    /**
     * 修改点
     * @param point
     * @return
     */
    @RequestMapping(value = "/updatePoint",method = RequestMethod.PUT)
    @ResponseBody
    public R updatePoint(HttpSession session,Point point){
        User user = (User) session.getAttribute("user");
        point.setCreateUser(user.getId());
        Integer result = pointService.updatePoint(point);
        if(result>0){
            return R.ok("添加成功").put("result",result);
        }else{
            return R.error("添加失败");
        }
    }

}
