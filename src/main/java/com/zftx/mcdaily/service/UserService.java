package com.zftx.mcdaily.service;

import com.zftx.mcdaily.bean.User;

import java.util.List;

public interface UserService {

    /**
     * 查   用户
     * @param user
     * @return
     */
    public List<User> getUser(User user);

    /**
     * 增   用户
     * @param user
     * @return
     */
    public String insertUser(User user);

    /**
     * 修   用户
     * @param user
     * @return
     */
    public String updateUser(User user);

    /**
     * 实际 删
     * @param user
     * @return
     */
    public String deleteUser(User user);
}
