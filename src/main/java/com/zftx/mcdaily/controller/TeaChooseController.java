package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.TeaChoose;
import com.zftx.mcdaily.service.TeaChooseService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class TeaChooseController {

    @Autowired
    private TeaChooseService teaChooseService;

    @RequestMapping(value = "/teaChoose")
    public String teaChoose(HttpSession session) {
        if (session.getAttribute("sessionUser") == null) {
            return "redirect:/login";
        }
        return "teaChoose";
    }

    /**
     * 查询 茶点选餐
     * @param teaChoose
     * @return
     */
    @RequestMapping(value = "/getTeaChoose", method = RequestMethod.GET)
    @ResponseBody
    public R getTeaChoose(TeaChoose teaChoose) {
        ArrayList<Map<String, Object>> list = teaChooseService.getTeaChoose(teaChoose);
        if (list.size() > 0 && list != null) {
            return R.ok("数据获取成功").put("data", list);
        } else if(list.size()==0) {
            return R.error("数据为空");
        } else{
            return R.error("获取数据失败");
        }
    }

    /**
     * 添加 茶点选餐
     * @param teaChoose
     * @return
     */
    @RequestMapping("/addTeaChoose")
    @ResponseBody
    public R addTeaChoose(TeaChoose teaChoose) {
        if(teaChoose!=null) {
            String str = teaChooseService.addTeaChoose(teaChoose);
            if ("success".equals(str)) {
                return R.ok("添加成功");
            } else if ("repeat".equals(str)) {
                return R.error("repeat");
            } else if("out".equals(str)){
                return R.error("out");
            }else{
                return R.error("添加失败");
            }
        }else{
            return R.error("参数有误!");
        }
    }

    /**
     * 修改 茶点选餐数量茶币
     * @param teaChoose
     * @return
     */
    @RequestMapping("/updateTeaChoose")
    @ResponseBody
    public R updateTeaChoose(TeaChoose teaChoose) {
        if(teaChoose!=null) {
            String str = teaChooseService.updateTeaChoose(teaChoose);
            if ("success".equals(str)) {
                return R.ok("修改成功");
            } else {
                return R.error("修改失败");
            }
        }else{
            return R.error("参数有误!");
        }
    }


    /**
     * 删除 茶点选餐
     * @param teaChoose
     * @return
     */
    @RequestMapping(value = "/deleteTeaChoose")
    @ResponseBody
    public R deleteTeaChoose(TeaChoose teaChoose) {
        if (teaChoose != null) {
            String str = teaChooseService.delTeaChoose(teaChoose);
            if ("success".equals(str)) {
                return R.ok("删除成功");
            } else {
                return R.error("删除失败");
            }
        } else {
            return R.error("参数有误!");
        }
    }

    @RequestMapping(value = "/isBeOutTenMoney")
    @ResponseBody
    public R isBeOutTenMoney(TeaChoose teaChoose,Integer teaPrice) {
        if (teaChoose != null&&teaPrice!=null&&teaPrice!=0){
            String str = teaChooseService.isBeOutTenMoney(teaChoose,teaPrice);
            if ("out".equals(str)) {
                return R.error("out");
            } else {
                return R.ok(str);
            }
        } else {
            return R.error("参数有误!");
        }
    }

    //茶点  统计
    @RequestMapping(value = "/teaStatistics")
    public String teaStatistics(HttpSession session) {
       /* if (session.getAttribute("sessionUser") == null) {
            return "redirect:/login";
        }*/
        return "teaStatistics";
    }

    /**
     * 查询 茶点统计
     * @param teaChoose
     * @return
     */
    @RequestMapping(value = "/getTeatatistics", method = RequestMethod.GET)
    @ResponseBody
    public R getTeatatistics(TeaChoose teaChoose) {
        ArrayList<Map<String, Object>> list = teaChooseService.getTeaStatistics(teaChoose);
        if (list.size() > 0 && list != null) {
            return R.ok("数据获取成功").put("data", list);
        } else if(list.size()==0) {
            return R.error("数据为空");
        } else{
            return R.error("获取数据失败");
        }
    }

    @RequestMapping(value = "/teaDistribute")
    public String teaDistribute(HttpSession session) {
      /*  if (session.getAttribute("sessionUser") == null) {
            return "redirect:/login";
        }*/
        return "teaDistribute";
    }

    /**
     * 查询 茶点分发
     * @param teaChoose
     * @return
     */
    @RequestMapping(value = "/getTeaDistribute", method = RequestMethod.GET)
    @ResponseBody
    public R getTeaDistribute(TeaChoose teaChoose) {
        ArrayList<Map<String, Object>> list = teaChooseService.getTeaDistribute(teaChoose);
        ArrayList<Map<String, Object>> ulist = teaChooseService.getTeaUser(teaChoose);
        if (list.size() > 0 && list != null) {
            return R.ok("数据获取成功").put("data", list).put("ulist", ulist);
        } else if(list.size()==0) {
            return R.error("数据为空");
        } else{
            return R.error("获取数据失败");
        }
    }
}
