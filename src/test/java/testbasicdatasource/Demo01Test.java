package testbasicdatasource;

import Mrchenli.BankFixture;
import org.junit.Test;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo01Test extends BankFixture {

    @Test
    public void test01() throws SQLException {
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(true);
            does(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("amount is ==>"+getInsuranceAmount(2222));
    }

    public void does(Connection connection) throws SQLException {
        String sql = "UPDATE INSURANCE_ACCOUNT SET INSURANCE_AMOUNT =INSURANCE_AMOUNT + ? WHERE INSURANCE_ID = ?";
        PreparedStatement updateStatement = connection.prepareStatement(sql);
        updateStatement.setFloat(1, 100.0F);
        updateStatement.setInt(2, 2222);
        updateStatement.execute();
        updateStatement.close();
        throw new RuntimeException();
    }
}
