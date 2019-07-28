package com.qgwy.alpha_web_manager.Shiro.controller;


import com.qgwy.alpha_web_manager.Shiro.entity.SysRole;
import com.qgwy.alpha_web_manager.Shiro.entity.UserInfo;
import com.qgwy.alpha_web_manager.Shiro.repository.SysRoleRepository;
import com.qgwy.alpha_web_manager.Shiro.repository.UserInfoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by archerlj on 2017/7/4.
 */

@Controller
@RequestMapping("shiroTest")
@Api(tags = "shiro测试")
public class RegisterController {

    @Resource
    private SysRoleRepository sysRoleRepository;
    @Resource
    private UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @ApiOperation("注册-get方式")
    public String register(Map<String, Object> map) {

        List<String> names = new LinkedList<>();
        List<SysRole> roleList = (List<SysRole>) sysRoleRepository.findAll();
        for (SysRole role : roleList) {
            names.add(role.getDescription());
        }
        map.put("roles", names);

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation("注册-post方式")
    public String register(@RequestParam("username") String userName,
                           @RequestParam("password") String password,
                           @RequestParam("passwordAgain") String passwordAgain,
                           HttpServletRequest request,
                           Map<String, Object> map) {
        if (!password.equals(passwordAgain)) {
            map.put("msg", "密码不一致");
            return "register";
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userName);
        userInfo.setState((byte) 1);
        userInfo.setSalt(createSalt().toString());
        String passwordMd5= new Md5Hash(password,userInfo.getCredentialsSalt(),2).toHex();
        userInfo.setPassword(passwordMd5);

        String[] roleNames = request.getParameterValues("checkbox");
        List<SysRole> roles = sysRoleRepository.findAllByDescriptionIn(Arrays.asList(roleNames));
        userInfo.setRoleList(roles);

        userInfoRepository.save(userInfo);
        return "login";
    }

    /**
     * 生成盐
     * @return
     */
    public static byte[] createSalt(){
        byte[] salt = new byte[16];
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.nextBytes(salt);
            return salt;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
