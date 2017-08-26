package Mrchenli.step1_failure;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FailureInsuranceDao {

    private DataSource dataSource;

    public FailureInsuranceDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void deposit(int insurancceId,float amount) throws SQLException {

        if(!isExistId(insurancceId)) throw new RuntimeException("no insuranceId");

        Connection connection = dataSource.getConnection();
        String sql = "UPDATE INSURANCE_ACCOUNT SET INSURANCE_AMOUNT =INSURANCE_AMOUNT + ? WHERE INSURANCE_ID = ?";
        PreparedStatement updateStatement = connection.prepareStatement(sql);
        updateStatement.setFloat(1,amount);
        updateStatement.setInt(2,insurancceId);
        updateStatement.execute();

        updateStatement.close();
        connection.close();
    }

    private boolean isExistId(int insuranceId) throws SQLException {

        Connection connection = dataSource.getConnection();
        String sql = "SELECT count(1) FROM INSURANCE_ACCOUNT WHERE INSURANCE_ID = ?";
        PreparedStatement selectStatement = connection.prepareStatement(sql);
        selectStatement.setInt(1,insuranceId);
        ResultSet resultSet = selectStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1)==1;
    }
}
