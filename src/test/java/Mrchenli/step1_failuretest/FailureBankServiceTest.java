package Mrchenli.step1_failuretest;

import Mrchenli.BaseTest;
import Mrchenli.service.step1_failure.FailureBankServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

public class FailureBankServiceTest extends BaseTest{

    @Override
    public void initBankService() {
        bankService =  new FailureBankServiceImpl(dataSource);
    }

    /**
     * 出账 入账 都 各自持有连接 如果出账成功入账异常就有问题了
     * @throws SQLException
     */
    @Test
    public void transferSuccessTest() throws SQLException {
        transferSuccess();
    }

    /**
     * 这里运行会出错 a账户扣了200 b账户不存在 应该回滚 没回滚
     * @throws SQLException
     */
    @Test
    public void transferFailureTest() throws SQLException {
        transferFailure();
    }

}
