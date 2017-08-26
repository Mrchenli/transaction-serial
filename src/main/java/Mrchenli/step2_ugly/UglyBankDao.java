package Mrchenli.step2_ugly;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UglyBankDao {

    public void withdraw(int bankId, float amount , Connection connection) throws SQLException {

        if(!isExistId(bankId,connection)) throw new RuntimeException("no bankId");

        String sql = "UPDATE BANK_ACCOUNT SET BANK_AMOUNT = BANK_AMOUNT - ? WHERE BANK_ID = ?";
        PreparedStatement updateStatement = connection.prepareStatement(sql);
        updateStatement.setFloat(1,amount);
        updateStatement.setInt(2,bankId);
        updateStatement.execute();

        updateStatement.close();
        connection.clearWarnings();
    }

    private boolean isExistId(int bankId,Connection connection) throws SQLException {
        String sql = "SELECT count(1) FROM BANK_ACCOUNT WHERE BANK_ID = ?";
        PreparedStatement selectStatement = connection.prepareStatement(sql);
        selectStatement.setInt(1,bankId);
        ResultSet resultSet = selectStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1)==1;
    }




}
