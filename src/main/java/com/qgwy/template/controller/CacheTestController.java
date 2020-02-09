/*
package com.qgwy.template.controller;


import com.qgwy.template.bean.Town;
import com.qgwy.template.service.TownService;
import com.qgwy.template.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/cache")
public class CacheTestController {

    @Autowired
    private TownService townService;

    @GetMapping("/getTownList")
    @ResponseBody
    public R getTownList(Integer id){
        Long start = System.currentTimeMillis();
        Town towns = townService.getTownList(id);
        Long end = System.currentTimeMillis();
        return R.ok().put("time",(end-start)).put("data",towns);
    }

    @PostMapping("/updateTown")
    @ResponseBody
    public R updateTown(String name,Integer id){
        Town town = townService.updateTown(name,id);
        return R.ok().put("data",town);
    }

    @PostMapping("/deleteTownById")
    @ResponseBody
    public R deleteTownById(Integer id){
        Integer result = townService.deleteTownById(id);
        if(result > 0){
            return R.ok().put("result",result);
        }else{
            return R.error();
        }
    }

    @GetMapping("/deleteAllEntries")
    @ResponseBody
    public R deleteAllEntries(){
        townService.deleteAllEntries();
        return R.ok().put("message","删除town下的所有缓存");
    }
}
*/
