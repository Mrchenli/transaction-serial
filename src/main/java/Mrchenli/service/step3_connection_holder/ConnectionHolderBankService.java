package Mrchenli.service.step3_connection_holder;

import Mrchenli.dao.step3_connection_holder.ConnectionHolderBankDao;
import Mrchenli.dao.step3_connection_holder.ConnectionHolderInsuranceDao;
import Mrchenli.dao.step3_connection_holder.TransactionManager;
import Mrchenli.service.AbstractService;

import javax.sql.DataSource;

public class ConnectionHolderBankService extends AbstractService {

    private TransactionManager transactionManager;

    public ConnectionHolderBankService(DataSource dataSource) {
        super(new ConnectionHolderBankDao(dataSource),
                new ConnectionHolderInsuranceDao(dataSource));
        this.transactionManager = new TransactionManager(dataSource);
    }

    @Override
    public void transfer(int fromId, int toId, float amount) {
        try{
            transactionManager.start();
            bankDao.withdraw(fromId,amount);
            insuranceDao.deposit(toId,amount);
            transactionManager.commit();
        }catch (Exception e){
            transactionManager.rollback();
        }finally {
            transactionManager.close();
        }
    }
}
