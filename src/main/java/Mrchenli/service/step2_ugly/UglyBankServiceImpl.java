package Mrchenli.service.step2_ugly;

import Mrchenli.dao.step1_failure.FailureBankDao;
import Mrchenli.dao.step1_failure.FailureInsuranceDao;
import Mrchenli.dao.step2_ugly.UglyBankDao;
import Mrchenli.dao.step2_ugly.UglyInsuranceDao;
import Mrchenli.service.AbstractService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class UglyBankServiceImpl extends AbstractService {

    /**
     * 这个要datasource来进行事物的处理 但是开了session后必须后传 所以后面就不需要datasource来获取
     * session
     * 这种方法就耦合了 service 层和 dao 层
     */
    protected DataSource dataSource;

    public UglyBankServiceImpl(DataSource dataSource) {
        super(new UglyBankDao(), new UglyInsuranceDao());
        this.dataSource = dataSource;
    }

    /**
     * 事物这段重复代码还是贼烦的
     * @param fromId
     * @param toId
     * @param amount
     */
    @Override
    public void transfer(int fromId, int toId, float amount) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            bankDao.withdraw(fromId,amount,connection);
            insuranceDao.deposit(toId,amount,connection);
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
