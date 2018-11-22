package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Surface;
import com.zftx.mcdaily.service.SurfaceService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public R getSurface(Surface surface){
        List<Surface> surfaceList = surfaceService.findAllSurFace(surface);
        if(surfaceList.size()>0&&surface != null){
            return R.ok("数据获取成功").put("data",surfaceList);
        }else{
            return R.error("数据获取失败");
        }
    }
}
