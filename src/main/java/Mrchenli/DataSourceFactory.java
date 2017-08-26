package Mrchenli;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUsername("SA");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:hsqldb:mem:bank");
    }

    public static DataSource createDataSource(){
        return dataSource;
    }

}
