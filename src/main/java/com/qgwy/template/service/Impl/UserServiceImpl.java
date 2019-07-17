package com.qgwy.template.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qgwy.template.bean.User;
import com.qgwy.template.mapper.UserMapper;
import com.qgwy.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加一个用户，单个添加 直接调用mybatis-plus封装好的方法，无需写xml文件
     * @param user
     * @return
     */
    @Override
    public Integer insertUser(User user){
        //返回一个数字：返回插入的条数
        return userMapper.insert(user);
    }


    /**
     * 批量添加
     * @param users
     * @return
     */
    @Override
    public Boolean batchSaveUser(List<User> users,Integer batchSize){
        //注意这里调用的是IService<T>里面的方法，并不是BaseMapper<T>的基类方法
        boolean batch = this.saveBatch(users,batchSize);
        return batch;
    }

    /**
     * 根据主键ID删除数据
     * @param id
     * @return
     */
    @Override
    public Integer deleteUserById(Integer id){
        return userMapper.deleteById(id);
    }


    /**
     * 根据条件构造器构造好的删除条件进行删除
     * @param username 用户名
     * @param gender 性别
     * @return
     */
    @Override
    public Integer deleteUserByQueryWrapper(String username,Integer gender){
        //声明一个条件构造器对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //构造条件，具体的方法可以查询官网的条件构造器，里面介绍的比较清楚
        //1、第一个条件是：模糊匹配用户名，使用like这个条件构造，这里的like对应数据库的“like”
        queryWrapper.like("username",username);
        //2、第二个条件是:根据性别，eq对应数据库的 “=”号
        queryWrapper.eq("gender",gender);
        //通过userMapper调用BaseMapper<T> 的delete方法，需要传入一个条件构造器
        return userMapper.delete(queryWrapper);
    }

    /**
     * 根据主键id进行更新
     * @param user
     * @return
     */
    @Override
    public Integer updateUserById(User user){
        return userMapper.updateById(user);
    }

    /**
     * 根据构造条件更新
     * @param username
     * @return
     */
    @Override
    public Boolean batchUpdateByWrapper(User user,String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",username);
        return this.update(user,queryWrapper);
    }

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public List<User> getUserList(String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",username);
        return userMapper.selectList(queryWrapper);
    }

    /**
     * 批量修改
     * @param userList
     * @return
     */
    @Override
    public Boolean batchUpdateUser(List<User> userList,Integer batchSize){
        //batchSize 是每一次更新的数据量大小
        return this.updateBatchById(userList,batchSize);
    }
}
