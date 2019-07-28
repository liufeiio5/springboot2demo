package com.qgwy.alpha_web_manager.Shiro.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by archerlj on 2017/6/30.
 */

@Controller
@RequestMapping("shiroTest")
@Api(tags = "shiro测试")
public class UserInfoController {

    /**
     * 用户查询.
     * @return
     */
    @GetMapping("/userList")
    @RequiresPermissions("userInfo:view")
    @ApiOperation("页面跳转-用户查询")
    public String userInfo(){
        return "userInfo";
    }

    /**
     * 用户添加;
     * @return
     */
    @GetMapping("/userAdd")
    @RequiresPermissions("userInfo:add")
    @ApiOperation("页面跳转-新增用户")
    public String userInfoAdd(){
        return "userInfoAdd";
    }

    @GetMapping("/userDel")
    @RequiresPermissions("userInfo:del")
    @ApiOperation("页面跳转-删除用户")
    @ExceptionHandler(UnauthorizedException.class)
    public String userInfoDel() {
        return "userInfoDel";
    }
}
