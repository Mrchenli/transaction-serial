package Mrchenli.step1_failure;

import Mrchenli.BankService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class FailureBankService implements BankService {

    private FailureBankDao failureBankDao;
    private FailureInsuranceDao failureInsuranceDao;
    private DataSource dataSource;

    public FailureBankService(FailureBankDao failureBankDao, FailureInsuranceDao failureInsuranceDao, DataSource dataSource) {
        this.failureBankDao = failureBankDao;
        this.failureInsuranceDao = failureInsuranceDao;
        this.dataSource = dataSource;
    }

    @Override
    public void transfer(int fromId, int toId, float amount) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(true);
            failureBankDao.withdraw(fromId,amount);
            failureInsuranceDao.deposit(toId,amount);
            connection.commit();
        }catch(Exception e){
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }

}
