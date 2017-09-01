package Mrchenli.myspring.demo.transactionpackage;

import io.mrchenli.aop.aopserial.ioc.annotation.Component;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

@Component("dataSource")
public class MyDataSource extends BasicDataSource implements DataSource {

    public MyDataSource() {
        this.setDriverClassName("org.hsqldb.jdbcDriver");
        this.setUsername("SA");
        this.setPassword("");
        this.setUrl("jdbc:hsqldb:mem:bank");
    }


    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
