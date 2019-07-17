package com.project.template.mapper;

import com.project.template.bean.User2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * t_user 操作
 *
 * @author Levin
 * @since 2018/5/7 0007
 */
@Repository
public interface User2Repository extends JpaRepository<User2, Long> {

    /**
     * 根据用户名查询用户信息
     *
     * @param name 用户名
     * @return 查询结果
     */
    List<User2> findAllByName(String name);

}
