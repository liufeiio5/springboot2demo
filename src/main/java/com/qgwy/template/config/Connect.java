package com.qgwy.template.config;

import java.sql.Connection;
import java.sql.SQLException;
import com.qgwy.template.util.DynamicDataSource;
import org.apache.logging.log4j.core.appender.db.jdbc.ConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Slf4j
@Component
public class Connect implements ConnectionSource {
 

	@Autowired
	private DataSource firstDataSource;
	
	@Override
	public Connection getConnection() throws SQLException {
		log.info("获取一个数据库连接");
		return firstDataSource.getConnection();
	}
}