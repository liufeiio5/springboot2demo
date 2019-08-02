/*
package com.qgwy.template.config;

import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.db.jdbc.ColumnConfig;
import org.apache.logging.log4j.core.appender.db.jdbc.ConnectionSource;
import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.filter.MarkerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class LogAppender {
	
	@Autowired
	private Connect connect;
 
	@PostConstruct
	public void init() throws SQLException {
		final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		final Configuration config = ctx.getConfiguration();
		final Logger interLogger = ctx.getLogger("com.qgwy");  //需要写日志到数据库的包名
		
		ColumnConfig[] cc = {
	            ColumnConfig.createColumnConfig(config, "CLASS", "%C", null, null, "false", null),
	            ColumnConfig.createColumnConfig(config, "FUNCTION", "%M", null, null, "false", null),
	            ColumnConfig.createColumnConfig(config, "LEVEL", "%level", null, null, "false", null),
	            ColumnConfig.createColumnConfig(config, "LOGGER", "%logger", null, null, "false", null),
	            ColumnConfig.createColumnConfig(config, "MESSAGE", "%message", null, null, "false", null),
	            ColumnConfig.createColumnConfig(config, "LOG_DATE", null, null, "true", null, null)
	    } ;  
		
	    //配置Marker过滤器(标记过滤器)
		MarkerFilter filter = MarkerFilter.createFilter("dblog", Filter.Result.ACCEPT, Filter.Result.DENY);
		
		Appender appender = JdbcAppender.createAppender("databaseAppender", "true", filter, connect, "0", "sys_logs", cc);

		config.addAppender(appender);
		interLogger.addAppender(appender);
		appender.start();
	        ctx.updateLoggers();    
	}
}
*/
