package Mrchenli.service;

import Mrchenli.dao.BankDao;
import Mrchenli.dao.InsuranceDao;
import Mrchenli.dao.step1_failure.FailureBankDao;
import Mrchenli.dao.step1_failure.FailureInsuranceDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class FailureBankServiceImpl extends AbstractService {

    protected DataSource dataSource;

    public FailureBankServiceImpl(DataSource dataSource) {
        super(new FailureBankDao(dataSource), new FailureInsuranceDao(dataSource));
        this.dataSource = dataSource;
    }

    @Override
    public void transfer(int fromId, int toId, float amount) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            bankDao.withdraw(fromId,amount);
            insuranceDao.deposit(toId,amount);
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
