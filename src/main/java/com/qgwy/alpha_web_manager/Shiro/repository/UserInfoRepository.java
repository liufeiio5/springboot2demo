package com.qgwy.alpha_web_manager.Shiro.repository;

import com.qgwy.alpha_web_manager.Shiro.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by archerlj on 2017/6/30.
 */
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

    public UserInfo findByUsername(String username);

    public UserInfo save(UserInfo userInfo);
}
