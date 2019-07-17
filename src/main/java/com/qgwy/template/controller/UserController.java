package com.qgwy.template.controller;

import com.qgwy.template.bean.User;
import com.qgwy.template.service.UserService;
import com.qgwy.template.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/user")
@Api(tags = "用户表操作接口")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/insertUser")
    @ResponseBody
    @ApiOperation("新增用户")
    public R insertUser(){
        User user = new User();
        user.setUsername("周二");
        user.setAge(20);
        user.setGender(0);
        user.setCreateTime(new Date());
        Integer result = userService.insertUser(user);
        return R.ok().put("result",result);
    }

    /**
     * 批量操作插入
     * @return
     */
    @RequestMapping(value = "/batchSaveUser")
    @ResponseBody
    @ApiOperation("批量插入")
    public R batchSaveUser(Integer batchSize){
        //1、先构造一个List<User>
        List<User> userList = new ArrayList<>();
        for(int i=0;i<10000;i++){
            User user = new User();
            user.setUsername("陈平安"+i+"号");
            user.setAge(i);
            if(i%2==0){
                user.setGender(1);
            }else{
                user.setGender(0);
            }
            user.setCreateTime( new Date());
            userList.add(user);
        }
        Long start = System.currentTimeMillis();
        boolean batch = userService.batchSaveUser(userList,batchSize);
        Long end = System.currentTimeMillis();
        System.out.println(">>>>>>>>>>>>>>>>>>耗时："+(end-start));
        if (batch){
            return R.ok("批量插入成功").put("batch",batch).put("耗时",end-start);
        }else{
            return R.error("批量插入失败").put("batch",batch);
        }
    }


    /**
     * 根据主键ID删除数据
     * @param Id
     * @return
     */
    @RequestMapping(value = "/deleteUserById")
    @ResponseBody
    @ApiOperation("根据主键删除数据")
    public R deleteUserById(Integer Id){

        if(Id != null){
            Integer result = userService.deleteUserById(Id);
            if(result != null){
                return R.ok("删除成功").put("result",result);
            }else{
                return R.error("删除失败").put("result",result);
            }
        }else{
            return R.error("参数不能为null");
        }
    }

    /**
     * 使用条件构造器删除数据
     * @param username
     * @param gender
     * @return
     */
    @RequestMapping(value = "/deleteUserByQueryWrapper")
    @ResponseBody
    @ApiOperation("根据条件构造器删除数据")
    public R deleteUserByQueryWrapper(String username,Integer gender){
        System.out.println("username: "+username +"   gender: "+gender);
        if(username != null && gender != null){
            Integer result = userService.deleteUserByQueryWrapper(username,gender);
            if(result != null){
                return R.ok("删除成功").put("result",result);
            }else{
                return R.error("删除失败");
            }
        }else{
            return R.error("参数错误1");
        }
    }

    /**
     * 根据主键ID修改
     * @return
     */
    @RequestMapping(value = "/updateUserById")
    @ResponseBody
    @ApiOperation("根据主键Id修改")
    public R updateUserById(){
        User user = new User();
        user.setId(2);
        user.setUsername("李四");
        user.setUpdateTime(new Date());
        Integer result = userService.updateUserById(user);
        if(result != null){
            return R.ok("修改成功").put("result",result);
        }else{
            return R.error("修改失败");
        }
    }

    /**
     * 根据构造条件修改数据
     * @param username
     * @return
     */
    @RequestMapping(value = "/batchUpdateByQueryWrapper")
    @ResponseBody
    @ApiOperation("根据构造条件修改")
    public R batchUpdateByQueryWrapper(String username){
        User user = new User();
        user.setGender(1);
        user.setUpdateTime(new Date());
        if(username != null){
            Boolean result = userService.batchUpdateByWrapper(user,username);
            if(result != null){
                return R.ok("修改成功").put("result",result);
            }else{
                return R.error("修改失败");
            }
        }else{
            return R.error("参数错误");
        }
    }

    @RequestMapping(value = "/getUserList")
    @ResponseBody
    @ApiOperation("查询用户数据")
    public R getUserList(String username){
        if(username != null){
            List<User> userList = userService.getUserList(username);
            if(userList != null && userList.size() > 0){
                return R.ok("查询成功").put("data",userList);
            }else{
                return R.error("查询失败");
            }
        }else{
            return R.error("参数不能未空");
        }
    }

    /**
     * 批量更新
     * @param username
     * @param batchSize
     * @return
     */
    @RequestMapping(value = "/batchUpdateUser")
    @ResponseBody
    @ApiOperation("批量更新")
    public R batchUpdateUser(String username, Integer batchSize){
        List<User> userList = userService.getUserList(username);
        if(userList != null && userList.size() > 0){
            for(int i=0;i<userList.size();i++){
                userList.get(i).setAge(new Random().nextInt(100)+2);
                userList.get(i).setUpdateTime(new Date());
            }
            Long start = System.currentTimeMillis();
            Boolean result = userService.batchUpdateUser(userList,batchSize);
            Long end = System.currentTimeMillis();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>耗时："+(end-start));
            if(result){
                return R.ok("批量更新成功20").put("result",result);
            }else{
                return R.error("批量更新失败");
            }
        }else{
            return R.error("没有查询到数据");
        }
    }
}
