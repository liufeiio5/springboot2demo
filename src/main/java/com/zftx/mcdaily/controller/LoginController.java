package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.bean.User;
import com.zftx.mcdaily.service.UserService;
import com.zftx.mcdaily.util.MD5;
import com.zftx.mcdaily.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes(value={"user","try"})
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 访问登录页
     * @return
     */
    @RequestMapping(value = "/login")
    public String Login(){
        System.out.println("aa");
        return "jsp/login";
    }

    /**
     * 主页
     * @param session
     * @return
     */
    @RequestMapping(value = "/home")
    public String home(HttpSession session)
    {
        if (session.getAttribute("sessionUser") == null)
            return "redirect:/login";
        return "home";
    }
    /**
     * 退出登录 注销
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) throws Exception {
        session.setAttribute("sessionUser",null);
        return "login";
    }

    /**
     * 用户登录
     * @param session
     * @param user
     * @return
     */
    @RequestMapping(value = "/userLogin",method = RequestMethod.GET)
    @ResponseBody
    public R login(HttpSession session, User user){
        user.setPassword(MD5.md5(user.getPassword(), user.getUserName()));
        String pwd=user.getPassword();
        List<User> list = userService.getUser(user.setPassword(null));
        if (list != null && list.size() > 0) {
            if(pwd.equals(list.get(0).getPassword())) {
                log.info(this.getClass() + " || " + Thread.currentThread().getStackTrace()[1].getMethodName() + " ## " + "参数：" + user + " message:登录成功");
                session.setAttribute("sessionUser",list.get(0));
                return R.ok().put("message", "登录成功!");
            }else{
                return R.error().put("message", "密码错误!");
            }
        } else {
            return R.error().put("message", "账号不存在!");
        }
    }


    /**
     * 注册，初始化调用（第一阶段内部使用初始化数据接口，主要是为了MD5 加密密码）
     * @param
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public R registers(String userName,String password,String fullName,String email,String phone,String birthplace,String birthday,String position,String hobby,String motto){
        String result = userService.insertUser(new User().setPassword(MD5.md5(password,userName)).setUserName(userName).setFullName(fullName).setEmail(email).setPhone(phone).
                setBirthplace(birthplace).setBirthday(birthday).setPosition(position).setHobby(hobby).setMotto(motto));
        if(result.equals("success")){
            return  R.ok("注册成功").put("result",result);
        }else{
            return R.error("注册失败").put("result",result);
        }
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public R getUser() {
        List<User> list = userService.getUser(new User());
        if (list != null && list.size() > 0) {
            return R.ok("数据获取成功").put("data", list);
        } else {
            return R.error("数据获取失败");
        }
    }
}
