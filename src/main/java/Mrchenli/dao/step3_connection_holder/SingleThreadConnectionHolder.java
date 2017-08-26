package Mrchenli.dao.step3_connection_holder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 这个是为了解决线程安全的一层
 * 每个线程对应一个map map里面一个数据源对应一个连接
 *
 * service层要获取这个连接来管理事物需要SingleThredConnectionHolder
 * dao层需要这个连接来进行数据库操作所以也需要SingleThreadConnectionHolder
 *
 */
public class SingleThreadConnectionHolder {

    private static ThreadLocal<ConnectionHolder> localConnectionHolder=new ThreadLocal<>();

    public static Connection getConnection(DataSource dataSource) throws SQLException {
        return getConnectionHolder().getConnection(dataSource);
    }

    public static void removeConnection(DataSource dataSource){
        getConnectionHolder().removeConnection(dataSource);
    }

    private static ConnectionHolder getConnectionHolder(){
        ConnectionHolder connectionHolder = localConnectionHolder.get();
        if(connectionHolder==null){
            connectionHolder = new ConnectionHolder();
            localConnectionHolder.set(connectionHolder);
        }
        return connectionHolder;
    }


}
