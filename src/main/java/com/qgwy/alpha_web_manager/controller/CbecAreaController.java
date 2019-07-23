package com.qgwy.alpha_web_manager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qgwy.alpha_web_manager.bean.CbecArea;
import com.qgwy.alpha_web_manager.service.CbecAreaService;
import com.qgwy.alpha_web_manager.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 全国区域信息 前端控制器
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@RestController
@RequestMapping("/cbec-area")
public class CbecAreaController {

    @Autowired
    private CbecAreaService cbecAreaService;

    @GetMapping(value = "/list")
    @ResponseBody
    public R list(){
        return R.ok().put("data",cbecAreaService.list(new QueryWrapper<CbecArea>()
                .select("id","area_level","province_code","province_name","city_code",
                        "city_name","area_code","area_name","street_code","street_name","lon"
                ,"lat","create_date")));
    }

}
