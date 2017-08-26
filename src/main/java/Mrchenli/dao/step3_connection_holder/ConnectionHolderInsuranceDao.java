package Mrchenli.dao.step3_connection_holder;

import Mrchenli.dao.AbstractInsuranceDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionHolderInsuranceDao extends AbstractInsuranceDao {

    private DataSource dataSource;

    public ConnectionHolderInsuranceDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean isConnectionClose() {
        return false;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return SingleThreadConnectionHolder.getConnection(dataSource);
    }

}
