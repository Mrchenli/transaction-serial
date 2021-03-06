package Mrchenli.dao.step3_connection_holder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个是为了实现多数据源的一层
 */
public class ConnectionHolder {

    private Map<DataSource,Connection> connectionMap = new HashMap<>();

    public Connection getConnection(DataSource dataSource) throws SQLException {
        Connection connection = connectionMap.get(dataSource);
        if(connection==null||connection.isClosed()){
            connection = dataSource.getConnection();
            connectionMap.put(dataSource,connection);
        }
        return connection;
    }

    public void removeConnection(DataSource dataSource){
        connectionMap.remove(dataSource);
    }


}
