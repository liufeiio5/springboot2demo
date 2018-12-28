package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Surface;
import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.SurfaceService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SurfaceController {

    @Autowired
    private SurfaceService surfaceService;

    /**
     * 获取面信息接口
     * @param surface
     * @return
     */
    @RequestMapping(value = "/getSurface",method = RequestMethod.GET)
    @ResponseBody
    public R getSurface(HttpSession session,Surface surface){
        User user = (User)session.getAttribute("sessionUser");
        List<Surface> surfaceList = surfaceService.findAllSurFace(surface.setCreateUser(user.getId()));
        if(surfaceList.size()>0&&surface != null){
            return R.ok("数据获取成功").put("data",surfaceList);
        }else{
            return R.error("数据获取失败");
        }
    }

    /**
     * 添加面信息
     * @param surface
     * @return
     */
    @RequestMapping(value = "/addSurface",method = RequestMethod.POST)
    @ResponseBody
    public R addSurface(HttpSession session, Surface surface){
        User user = (User) session.getAttribute("sessionUser");
        surface.setCreateUser(user.getId());
        Integer result = surfaceService.addSurface(surface);
        if(result>0){
            return R.ok("添加成功").put("result",result);
        }else{
            return R.error("添加失败");
        }
    }

    /**
     * 修改面信息
     */
    @RequestMapping(value = "/updateSurface",method = RequestMethod.PUT)
    @ResponseBody
    public R updateSurface(HttpSession session,Surface surface){
        User user = (User) session.getAttribute("user");
        surface.setCreateUser(user.getId());
        Integer result = surfaceService.updateSurface(surface);
        if(result>0){
            return R.ok("添加成功").put("result",result);
        }else{
            return R.error("添加失败");
        }
    }
}
