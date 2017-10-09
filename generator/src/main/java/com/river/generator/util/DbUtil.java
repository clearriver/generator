/**
 * Project Name:generator
 * File Name:DbUtil.java
 * Package Name:com.river.generator.util
 * Date:2017年10月06日下午6:00:34
 */

package com.river.generator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.river.generator.config.Configuration;

/**
 * ClassName:DbUtil <br/>
 * Function: 数据库连接工具类. <br/>
 * Date: 2017年10月06日 下午6:00:34 <br/>
 * 
 * @author wuqh
 * @version
 * @since JDK 1.6
 * @see
 */
public class DbUtil {
    /**
     * 日志类
     */
    private static Logger logger = LogManager.getLogger(DbUtil.class.getName());
    /**
     * getConn:获取连接. <br/>
     *
     * @author wuqh
     * @return
     * @since JDK 1.6
     */
    public static Connection getConn(String driverName,String jdbcUrl,String userName,String password) {
        Connection conn = null;
        try {
            Class.forName(driverName);
            logger.info("加载驱动成功：{}", driverName);
            
            Properties props = new Properties();
            props.put("remarksReporting", "true");
            props.put("user", userName);
            props.put("password", password);
            conn = DriverManager.getConnection(jdbcUrl, props);
        } catch (ClassNotFoundException e) {
            logger.error("数据连接异常", e);
            e.printStackTrace();
        } catch (SQLException e) {
            logger.error("数据连接异常", e);
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * closeConn:关闭连接. <br/>
     *
     * @author wuqh
     * @param conn
     * @since JDK 1.6
     */
    public static void closeReso(Connection conn, Statement stat, ResultSet resultSet) {
        try {
            if (conn != null) conn.close();
            if (stat != null) stat.close();
            if (resultSet != null) resultSet.close();
            logger.info("关闭资源成功。。。");
        } catch (SQLException e) {
            logger.error("关闭连接异常", e);
            e.printStackTrace();
        }
    }
}
