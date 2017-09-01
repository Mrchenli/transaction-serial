package Mrchenli.myspring.demo.dao;

import Mrchenli.dao.AbstractBankDao;
import Mrchenli.dao.BankDao;
import Mrchenli.dao.step3_connection_holder.SingleThreadConnectionHolder;
import io.mrchenli.aop.aopserial.ioc.annotation.Component;
import io.mrchenli.aop.aopserial.ioc.annotation.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component("bankDao")
public class BankDaoImpl extends AbstractBankDao implements BankDao{

    @Inject(name = "dataSource")
    private DataSource dataSource;


    @Override
    public boolean isConnectionClose() {
        return false;
    }


    @Override
    public Connection getConnection() throws SQLException {
        return SingleThreadConnectionHolder.getConnection(dataSource);
    }


}
