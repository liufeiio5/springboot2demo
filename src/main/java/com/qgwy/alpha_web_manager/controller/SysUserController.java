package com.qgwy.alpha_web_manager.controller;


import com.qgwy.alpha_web_manager.bean.SysUser;
import com.qgwy.alpha_web_manager.enums.ServiceCode;
import com.qgwy.alpha_web_manager.form.UserForm;
import com.qgwy.alpha_web_manager.service.SysUserService;
import com.qgwy.alpha_web_manager.util.R;
import com.qgwy.alpha_web_manager.util.RedisUtil;
import com.qgwy.alpha_web_manager.util.TokenUtils;
import com.qgwy.alpha_web_manager.util.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/sys-user")
@Slf4j
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户登录",notes = "用户名、密码验证登录")
    @PostMapping("/login")
    @ResponseBody
    public R login(@RequestBody UserForm userForm, HttpServletRequest request) {
        if(StringUtils.isEmpty(userForm.getUsername())) {
            return R.error("用户名不能为空");
        }
        if(StringUtils.isEmpty(userForm.getPassword())) {
            return R.error("密码不能为空");
        }
        //为调试方便，密码暂未加密
        SysUser user = sysUserService.findUserByUsernameAndPwd(userForm);
        if(user == null) {
            log.error("用户名或密码错误|前端输入的数据：userForm = {}",userForm);
            return R.error(ServiceCode.NAME_OR_PASS_ERROR.getCode(),ServiceCode.NAME_OR_PASS_ERROR.getMsg());
        }
        user.setLastLoginTime(new Date());
        //用户存在，生成token，将用户信息放入session和redis
        String token = TokenUtils.generatorToken(userForm.getUsername());
        request.getSession().setAttribute(token,user);
        log.info("SysUserController.login|将用户信息放入session user={}",user);
        //放入redis,设置过期时间为一天
        RedisUtil.set(token,user,24 * 60 * 60);
        log.info("SysUserController.login|将用户信息放入redis,过期时间为24小时 user={}",user);
        Map<String,Object> data = new HashMap<>();
        data.put("token",token);
        data.put("userInfo",user);
        return R.ok(data);
    }

    @ApiOperation(value = "退出登录",notes = "根据token退出用户")
    @GetMapping("/logout")
    public R logout(HttpServletRequest request) {
        String token = request.getHeader("token");
//        request.getSession().removeAttribute(token);
        //销毁session
        request.getSession().invalidate();
        log.info("LoginController.logout|session中成功退出用户 token={}",token);
        RedisUtil.del(token);
        log.info("LoginController.logout|redis中成功退出用户 token={}",token);
        return R.ok("退出成功");
    }

    @GetMapping("/list")
    @ResponseBody
    public List<SysUser> list(HttpServletRequest request){
        SysUser userInfo = UserUtils.getUserInfo(request);
        System.out.println("userInfo:{}"+userInfo);
        List<SysUser> list = sysUserService.list();
        return list;
    }


    @PostMapping("/save")
    @ResponseBody
    public boolean save(){
        SysUser sysUser = new SysUser();
        sysUser.setBirth(new Timestamp(System.currentTimeMillis()));
        sysUser.setName("test");
        sysUser.setUsername("test");
        sysUser.setPassword("123456");
        sysUser.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        boolean save = sysUserService.save(sysUser);
        return save;
    }

}
