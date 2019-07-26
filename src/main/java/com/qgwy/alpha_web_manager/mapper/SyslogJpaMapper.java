package com.qgwy.alpha_web_manager.mapper;

import com.qgwy.alpha_web_manager.bean.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * t_user 操作
 *
 * @author Levin
 * @since 2018/5/7 0007
 */
@Repository
public interface SyslogJpaMapper extends JpaRepository<SysLog, Integer> {



}
