package Mrchenli.service.step5_transaction_proxy;

import Mrchenli.dao.BankDao;
import Mrchenli.dao.InsuranceDao;
import Mrchenli.dao.step3_connection_holder.ConnectionHolderBankDao;
import Mrchenli.dao.step3_connection_holder.ConnectionHolderInsuranceDao;
import Mrchenli.service.AbstractService;
import Mrchenli.service.BankService;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 这个service就直接不依赖datasource了（构造器的依赖完全可以换成dao ,dao依赖datasource）
 */
public class TransactionProxyBankService extends AbstractService implements BankService{

    public TransactionProxyBankService(DataSource dataSource) {
        super(new ConnectionHolderBankDao(dataSource),
                new ConnectionHolderInsuranceDao(dataSource));
    }

    @Override
    public void transfer(int fromId, int toId, float amount) {
        try {
            bankDao.withdraw(fromId,amount);
            insuranceDao.deposit(toId,amount);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
