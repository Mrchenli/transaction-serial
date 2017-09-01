package Mrchenli.myspring.demo.transactionpackage;

import Mrchenli.dao.step3_connection_holder.SingleThreadConnectionHolder;
import io.mrchenli.aop.aopserial.ioc.annotation.Component;
import io.mrchenli.aop.aopserial.ioc.annotation.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * datasource是单例的
 * 讲道理只要SingleThreadConnectionHolder就够了的
 * 这个是对事物的流程做的一个封装
 */
@Component("transactionManager")
public class TransactionManager {

    @Inject(name = "dataSource")
    private DataSource dataSource;

    //      --thread1
    //          --ConnectionHolder
    //              --dataSource:connection
    //      --thread2
    //          --ConnectionHolder
    //              --dataSource:connection
    public void start() throws SQLException {
        Connection connection =getConnection();
        connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        Connection connection = getConnection();
        connection.commit();
    }

    public final void rollback(){
        Connection connection = null;
        try {
            connection = getConnection();
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't rollback on connection[" + connection + "].", e);
        }
    }

    public final void close(){
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(true);
            connection.setReadOnly(false);
            connection.close();
            SingleThreadConnectionHolder.removeConnection(dataSource);
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't close connection[" + connection + "].", e);
        }
    }


    private Connection getConnection() throws SQLException {
        return SingleThreadConnectionHolder.getConnection(dataSource);
    }



}
