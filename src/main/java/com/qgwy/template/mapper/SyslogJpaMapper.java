package com.qgwy.template.mapper;

import com.qgwy.template.bean.SysLog;
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
public interface SyslogJpaMapper extends JpaRepository<SysLog, Integer> {



}
