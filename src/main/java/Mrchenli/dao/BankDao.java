package Mrchenli.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface BankDao {

     void withdraw(int bankId, float amount) throws SQLException;

     void withdraw(int bankId, float amount,Connection connection) throws SQLException;

}
