package Mrchenli.dao.step1_failure;

import Mrchenli.dao.AbstractInsuranceDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class FailureInsuranceDao extends AbstractInsuranceDao {

    private DataSource dataSource;

    public FailureInsuranceDao(DataSource dataSource) {
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
