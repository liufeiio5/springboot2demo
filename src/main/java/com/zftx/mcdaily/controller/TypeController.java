package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.Type;
import com.zftx.mcdaily.service.TypeService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "/getType",method = RequestMethod.GET)
    @ResponseBody
    public R getType(Type type){
        List<Type> typeList = typeService.getType(type);
        if(typeList.size()>0&&typeList != null){
            return R.ok("数据获取成功").put("data",typeList);
        }else {
            return R.error("获取数据失败");
        }
    }
}
