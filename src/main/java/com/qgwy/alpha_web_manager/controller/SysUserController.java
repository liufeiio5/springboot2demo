package com.qgwy.alpha_web_manager.controller;


import com.qgwy.alpha_web_manager.bean.SysUser;
import com.qgwy.alpha_web_manager.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author buzhifeng
 * @since 2019-07-23
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/list")
    @ResponseBody
    public List<SysUser> list(){

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
