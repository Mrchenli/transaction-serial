package Mrchenli.dao.step3_connection_holder;

import Mrchenli.dao.AbstractBankDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionHolderBankDao extends AbstractBankDao{

    private DataSource dataSource;

    public ConnectionHolderBankDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * 对于tomcat来讲 一个请求一个线程 一个线程的connection是同一个
     * 所以不用关闭
     * @return
     */
    @Override
    public boolean isConnectionClose() {
        return false;
    }

    /**
     * 这个connection 是从SingleThreadConnectionHolder来的
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        return SingleThreadConnectionHolder.getConnection(dataSource);
    }

}
