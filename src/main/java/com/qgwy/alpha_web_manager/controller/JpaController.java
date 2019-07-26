package com.qgwy.alpha_web_manager.controller;

import com.qgwy.alpha_web_manager.annotation.MyLog;
import com.qgwy.alpha_web_manager.bean.User2;
import com.qgwy.alpha_web_manager.mapper.User2Repository;
import com.qgwy.alpha_web_manager.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("jpa")
@Slf4j
@Api(tags = "jpa测试")
public class JpaController {

    @Autowired
    private User2Repository user2Repository;

    @PostMapping("/save")//保存用户
    @ResponseBody
    @MyLog("增加用户")
    @ApiOperation(value ="1-jpa-保存用户",notes = "测试用jpa插入一条数据")
    public R saveUser(User2 user) {
        if (user != null && !StringUtils.isEmpty(user.getName())) {
            User2 user2 = user2Repository.save(user);
            if (user2 != null) {
                log.info("用户：{}，插入成功！");
                return R.ok().put("data", user);
            }
        }
        return R.error().put("data", "").put("msg", "该用户信息为空，禁止插入");
    }

    @RequestMapping("/get/{userId}")//根据ID查询用户
    @ResponseBody
    @Cacheable(cacheNames = "user")
    public R getUserById(@PathVariable("userId") long userId) {
        if (!StringUtils.isEmpty(userId)) {
            User2 user2 = user2Repository.getOne(userId);
            if (user2 != null) {
                log.info("获取用户：{}，成功！", user2);
                return R.ok().put("data", user2.toString());
            }
        }
        return R.error().put("data", "").put("msg", "用户信息为空，请输入有效信息");
    }

    @RequestMapping("/getByName/{name}")//根据name查询用户,返回的是一个或单个
    @ResponseBody
    public R getUserByName(@PathVariable("name") String username) {
        if (!StringUtils.isEmpty(username)) {
            List<User2> user2 = user2Repository.findAllByName(username);
            if (user2 != null) {
                log.info("获取用户：{}，成功！", user2);
                return R.ok().put("data", user2.toString());
            }
        }
        return R.error().put("data", "").put("msg", "用户信息为空，请输入有效信息");
    }

    @RequestMapping("/getUserByPage/{pageNo}/{pageSize}/{sortField}")//分页查询
    @ResponseBody
    public R getUserByPage(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, @PathVariable("sortField") String sortField) {

        Pageable pageAble = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.desc("name")));
        Page<User2> all = user2Repository.findAll(pageAble);
        log.info("获取用户：{}，成功！", all.getContent());
        return R.ok().put("data", all.getContent().toString());
    }

    @RequestMapping("/updateUser/{username}/{phone}")//更新
    @ResponseBody
    public R updateUser(@PathVariable("username") String username, @PathVariable("phone") String phone) {

        User2 update = user2Repository.save(new User2().setName(username).setPhone(phone).setId(5));
        log.info("获取用户：{}，成功！", update);
        return R.ok().put("data", update);
    }

    @RequestMapping("/deleteUserById/{id}")//删除用户
    @ResponseBody
    @CacheEvict(value = "user",beforeInvocation=false)
    public R deleteUserById(@PathVariable("id") long id) {

        User2 one = user2Repository.getOne(id);
        if (one.getName() != null) {
            user2Repository.deleteById(id);
            log.info("用户：{}/t已被删除！", one);
            return R.ok().put("data", one.toString()).put("msg", "用户：" + one + " 已被删除！");
        }

        return R.ok().put("data", one.toString()).put("msg", "没有用户被删除！");
    }

    @RequestMapping("/test/{userId}")//测试是否正常返回
    @ResponseBody
    public R test(@PathVariable("userId") long userId) {
        return R.ok().put("data", new User2().setId(userId).setName("nmd"));

    }
}
