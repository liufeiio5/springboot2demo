package com.qgwy.template.controller;

import com.qgwy.template.bean.User;
import com.qgwy.template.service.UserService;
import com.qgwy.template.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
        //容量大小
        int length = 10000;
        for(int i=1;i<=length;i++){
            User user = new User();
            user.setUsername("陈平安"+i+"号");
            user.setAge(i);
            if(i%2==0){
                user.setGender(1);
            }else{
                user.setGender(0);
            }
            user.setCreateTime(new Date());
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
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUserById")
    @ResponseBody
    @ApiOperation("根据主键删除数据")
    public R deleteUserById(Integer id){

        if(id != null){
            Integer result = userService.deleteUserById(id);
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

    @RequestMapping(value = "insertUserByBatch")
    @ResponseBody
    @ApiOperation("使用mybatis的批量插入")
    public R insertUserByBatch(Integer size){
        List<User> userList = new ArrayList<>();
        Long t = System.currentTimeMillis();
        //构造数据
        for(int i = 1;i <= size;i++){
            User user = new User();
            user.setUsername("裴钱"+i+"号");
            user.setAge(16);
            user.setGender(0);
            user.setCreateTime(new Date());
            userList.add(user);
        }
        Long n = System.currentTimeMillis();

        //分批次提交数量
        int insertFlag = 20000;
        //插入返回的总数
        Integer result = 0;
        //标记当前是第几次
        int flag = 0;
        //计算总的插入提交次数
        int s = size/insertFlag;
        //每次插入的数据保存在一个新的list中，每次插入完成后清空list
        List<User> insertUsers = new ArrayList<>();
        Long start = System.currentTimeMillis();
        //循环数据源list
        for(User user : userList){
            //将数据保存到插入list中
            insertUsers.add(user);
            //如果是最后一次的,将剩余的元素全部获取
            if(s==flag){
                //使用当前已经插入的总条数+1,和数据源的size 截取剩余的数据
                insertUsers.addAll(userList.subList(result+1,userList.size()));
                //执行插入操作，将插入成功的条数累加到result中
                result += userService.insertUserByBatch(insertUsers);
                //插入次数自增
                flag++;
                //清空list
                insertUsers.clear();
                //最后一次执行插入，插入操作完成，终止循环
                break;
            }
            //判断插入list的数据量是否达到插入标记提交数量，是--执行插入，否--继续往插入list中添加数据
            if(insertUsers.size() == insertFlag ){
                //将插入成功的数据累加
                result += userService.insertUserByBatch(insertUsers);
                //插入次数自增
                flag++;
                //清空插入list，进行下一次循环
                insertUsers.clear();
            }
        }
        System.out.println(">>>>>>>:"+s);
        Long end = System.currentTimeMillis();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>耗时(s)："+(end-start)/1000);
        if(result != null){
            return R.ok().put("result",result).put("耗时(s)",end-start).put("次数",flag).put("构造数据耗时：",n-t);
        }else{
            return R.error();
        }
    }
}
