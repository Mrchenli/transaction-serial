package Mrchenli.dao.step4_transaction_template;

import Mrchenli.dao.step3_connection_holder.TransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

public abstract class TransactionTemplate {

    private TransactionManager transactionManager;

    protected TransactionTemplate(DataSource dataSource) {
        this.transactionManager = new TransactionManager(dataSource);
    }

    public void doJobInTransaction(){
        try{
            transactionManager.start();
            doJob();
            transactionManager.commit();
        }catch (Exception e){
            transactionManager.rollback();
        }finally {
            transactionManager.close();
        }
    }

    protected abstract void doJob() throws SQLException;
}
