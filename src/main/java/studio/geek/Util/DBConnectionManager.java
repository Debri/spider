package studio.geek.Util;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Liuqi
 * Date: 2017/4/7.
 */
public class DBConnectionManager {
    private static Logger logger = SimpleLogger.getSimpleLogger(DBConnectionManager.class);
    private static Connection conn;

    public static Connection getConnection() {
//获取数据库连接
        try {
            if (conn == null || conn.isClosed()) {
                conn = createConnection();
            } else {
                return conn;
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }
        return conn;
    }

    static {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");//加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection createConnection() {
        Properties p = new Properties();
        Connection conn = null;
        try {
            p.load(DBConnectionManager.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String host = p.getProperty("db.host");
        String username = p.getProperty("db.username");
        String password = p.getProperty("db.password");
        String dbName = p.getProperty("db.name");
        String url = "jdbc:mysql://" + host + ":3306/" + dbName;
        try {
            conn = DriverManager.getConnection(url, username, password);//建立mysql的连接
            logger.debug("success!");
        } catch (MySQLSyntaxErrorException e) {
            logger.error("数据库不存在..请先手动创建创建数据库:" + dbName);
            e.printStackTrace();
        } catch (SQLException e2) {
            logger.error("SQLException", e2);
        }
        return conn;
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
