package com.qgwy.template.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qgwy.template.bean.User;

import java.util.List;

public interface UserService extends IService<User> {

    /**
     * 添加一条数据
     * @param user
     * @return
     */
    Integer insertUser(User user);

    /**
     * 批量添加
     * @param users
     * @return
     */
    Boolean batchSaveUser(List<User> users,Integer batchSize);


    /**
     * 根据主键删除
     * @param id
     * @return
     */
    Integer deleteUserById(Integer id);

    /**
     * 根据条件构造器构造好的删除条件进行删除
     * @param username 用户名
     * @param gender 性别
     * @return
     */
    Integer deleteUserByQueryWrapper(String username,Integer gender);

    /**
     * 根据主键进行更新
     * @param user
     * @return
     */
    Integer updateUserById(User user);

    /**
     * 根据构造条件更新
     * @param username
     * @return
     */
    Boolean batchUpdateByWrapper(User user,String username);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    List<User> getUserList(String username);

    /**
     * 批量修改
     * @param userList
     * @return
     */
    Boolean batchUpdateUser(List<User> userList,Integer batchSize);

    /**
     * 批量插入用户，使用mybatis的动态SQL foreach
     * @param userList
     * @return
     */
    Integer insertUserByBatch(List<User> userList);
}
