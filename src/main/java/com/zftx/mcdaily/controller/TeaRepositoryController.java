package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.TeaRepository;
import com.zftx.mcdaily.service.TeaRepositoryService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class TeaRepositoryController {

    @Autowired
    private TeaRepositoryService teaRepositoryService;

    @RequestMapping(value ="/teaRepository")
    public String  teaRepository(){
        return "teaRepository";
    }
    /**
     * 查询 茶库茶点
     * @param teaRepository
     * @return
     */
    @RequestMapping(value = "/getTeaRepository",method = RequestMethod.GET)
    @ResponseBody
    public R getLine(TeaRepository teaRepository){
        ArrayList<Map<String,Object>> list = teaRepositoryService.getTeaRepository(teaRepository);
        if(list.size()>0&&list != null){
            return R.ok("数据获取成功").put("data",list);
        }else{
            return R.error("获取数据失败");
        }
    }

    /**
     * 添加 茶点 入库
     * @param teaRepository
     * @return
     */
    @RequestMapping(value = "/addTeaRepository")
    @ResponseBody
    public R addTeaRepository(TeaRepository teaRepository) {
        if (teaRepository != null) {
            String str = teaRepositoryService.addTeaRepository(teaRepository);
            if ("success".equals(str)) {
                return R.ok("添加成功");
            } else if ("repeat".equals(str)) {
                return R.error("重复添加");
            } else {
                return R.error("添加失败");
            }
        }else{
            return R.error("参数有误!");
        }
    }

    /**
     * 修改 茶库 茶点
     * @param teaRepository
     * @return
     */
    @RequestMapping(value = "/updateTeaRepository")
    @ResponseBody
    public R updateTeaRepository(TeaRepository teaRepository){
        if (teaRepository != null) {
            String str = teaRepositoryService.updateTeaRepository(teaRepository);
            if ("success".equals(str)) {
                return R.ok("修改成功");
            } else if ("repeat".equals(str)) {
                return R.error("该茶点已存在");
            } else {
                return R.error("修改失败");
            }
        }else{
            return R.error("参数有误!");
        }
    }

    /**
     * 删除 茶点
     * @param teaRepository
     * @return
     */
    @RequestMapping(value = "/deleteTeaRepository")
    @ResponseBody
    public R deleteTeaRepository(TeaRepository teaRepository){
        if (teaRepository != null) {
            String str = teaRepositoryService.delTeaRepository(teaRepository);
            if ("success".equals(str)) {
                return R.ok("删除成功");
            }else {
                return R.error("删除失败");
            }
        }else{
            return R.error("参数有误!");
        }
    }
}
