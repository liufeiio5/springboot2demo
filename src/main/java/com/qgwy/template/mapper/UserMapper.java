package com.qgwy.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qgwy.template.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 批量插入用户，使用mybatis的动态SQL foreach
     * @param list
     * @return
     */
    Integer insertUserByBatch(List<User> list);
}
