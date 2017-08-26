package Mrchenli.step2_ugly;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class UglyBankService {

    private DataSource dataSource;
    private UglyBankDao uglyBankDao;
    private UglyInsuranceDao uglyInsuranceDao;

    public UglyBankService(DataSource dataSource, UglyBankDao uglyBankDao, UglyInsuranceDao uglyInsuranceDao) {
        this.dataSource = dataSource;
        this.uglyBankDao = uglyBankDao;
        this.uglyInsuranceDao = uglyInsuranceDao;
    }

    public void transfer(int fromId, int toId, float amount ){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            uglyBankDao.withdraw(fromId,amount,connection);
            uglyInsuranceDao.deposit(toId,amount,connection);
            connection.commit();
        }catch (Exception e){
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }



}
