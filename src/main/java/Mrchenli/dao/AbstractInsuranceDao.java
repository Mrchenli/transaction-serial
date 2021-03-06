package Mrchenli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractInsuranceDao implements InsuranceDao{

    @Override
    public void deposit(int insuranceId, float amount) throws SQLException {
       Connection connection = getConnection();
       deposit(insuranceId,amount,connection);
    }

    @Override
    public void deposit(int insuranceId, float amount,Connection connection) throws SQLException {
        if(!isExistId(insuranceId,connection)) throw new  RuntimeException("no insuranceId");

        String sql = "UPDATE INSURANCE_ACCOUNT SET INSURANCE_AMOUNT =INSURANCE_AMOUNT + ? WHERE INSURANCE_ID = ?";
        PreparedStatement updateStatement = connection.prepareStatement(sql);
        updateStatement.setFloat(1, amount);
        updateStatement.setInt(2, insuranceId);
        updateStatement.execute();

        updateStatement.close();
        if(isConnectionClose()) connection.close();
    }



    private boolean isExistId(int bankId,Connection connection) throws SQLException {
        String sql = "SELECT count(1) FROM INSURANCE_ACCOUNT WHERE INSURANCE_ID = ?";
        PreparedStatement selectStatement = connection.prepareStatement(sql);
        selectStatement.setInt(1,bankId);
        ResultSet resultSet = selectStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1)==1;
    }

    public Connection getConnection() throws SQLException {return null;}


    public abstract boolean isConnectionClose();
}
