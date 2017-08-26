package Mrchenli.step1_failure;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FailureBankDao {

    private DataSource dataSource;

    public FailureBankDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void withdraw(int bankId,float amount) throws SQLException {
        if(!isExistId(bankId)) throw new  RuntimeException("no insuranceId");
        Connection connection = dataSource.getConnection();
        String sql = "UPDATE BANK_ACCOUNT SET BANK_AMOUNT = BANK_AMOUNT - ? WHERE BANK_ID = ?";
        PreparedStatement updateStatement = connection.prepareStatement(sql);
        updateStatement.setFloat(1,amount);
        updateStatement.setInt(2,bankId);
        updateStatement.execute();

        updateStatement.close();
        connection.close();
    }

    private boolean isExistId(int bankId) throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql = "SELECT count(1) FROM BANK_ACCOUNT WHERE BANK_ID = ?";
        PreparedStatement selectStatement = connection.prepareStatement(sql);
        selectStatement.setInt(1,bankId);
        ResultSet resultSet = selectStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1)==1;
    }

}
