package Mrchenli.dao.step1_failure;

import Mrchenli.dao.AbstractBankDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class FailureBankDao extends AbstractBankDao {

    private DataSource dataSource;

    public FailureBankDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public boolean isConnectionClose() {
        return true;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
