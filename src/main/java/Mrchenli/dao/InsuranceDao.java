package Mrchenli.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface InsuranceDao {

     void deposit(int insuranceId, float amount)throws SQLException;
     void deposit(int insuranceId, float amount,Connection connection)throws SQLException ;
}
