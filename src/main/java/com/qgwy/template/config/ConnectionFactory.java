/*
package com.qgwy.template.config;

import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;

import javax.management.ObjectName;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

 
public class ConnectionFactory {
         private static interface Singleton {
       final ConnectionFactory INSTANCE = new ConnectionFactory();
    }
 
   private final DataSource dataSource;
 
   private ConnectionFactory() {
             try {
           Class.forName("com.mysql.jdbc.Driver");
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
           System.exit(0);
       }
            
       Properties properties = new Properties();
       properties.setProperty("user", "root");
       properties.setProperty("password", "123456"); //or get properties from some configuration file
 
       GenericObjectPool<PoolableConnection> pool= new GenericObjectPool<PoolableConnection>();
       DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                "jdbc:mysql://192.168.100.36:3306/daily",properties
       );
       this.dataSource = new PoolingDataSource(pool);
       new PoolableConnectionFactory(connectionFactory, (ObjectName) dataSource);

    }
 
   public static Connection getDatabaseConnection() throws SQLException {
       return Singleton.INSTANCE.dataSource.getConnection();
    }
}*/
