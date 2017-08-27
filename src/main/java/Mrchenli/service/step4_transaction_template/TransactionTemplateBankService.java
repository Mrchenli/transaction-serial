package Mrchenli.service.step4_transaction_template;

import Mrchenli.dao.step3_connection_holder.ConnectionHolderBankDao;
import Mrchenli.dao.step3_connection_holder.ConnectionHolderInsuranceDao;
import Mrchenli.dao.step4_transaction_template.TransactionTemplate;
import Mrchenli.service.AbstractService;

import javax.sql.DataSource;
import java.sql.SQLException;

public class TransactionTemplateBankService extends AbstractService {

    private DataSource dataSource;

    public TransactionTemplateBankService(DataSource dataSource) {
        super(new ConnectionHolderBankDao(dataSource),
                new ConnectionHolderInsuranceDao(dataSource));
        this.dataSource = dataSource;
    }

    /**
     * 第一次感受到匿名内部类的作用
     * @param fromId
     * @param toId
     * @param amount
     */
    @Override
    public void transfer(int fromId, int toId, float amount) {
        new TransactionTemplate(dataSource){
            @Override
            protected void doJob() throws SQLException {
                bankDao.withdraw(fromId,amount);
                insuranceDao.deposit(toId,amount);
            }
        }.doJobInTransaction();
    }
}
