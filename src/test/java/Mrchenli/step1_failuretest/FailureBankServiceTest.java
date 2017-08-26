package Mrchenli.step1_failuretest;

import Mrchenli.BankFixture;
import Mrchenli.step1_failure.FailureBankDao;
import Mrchenli.step1_failure.FailureBankService;
import Mrchenli.step1_failure.FailureInsuranceDao;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class FailureBankServiceTest extends BankFixture{

    /**
     * 出账 入账 都 各自持有连接 如果出账成功入账异常就有问题了
     * @throws SQLException
     */
    @Test
    public void transferSuccess() throws SQLException {
        FailureBankDao failureBankDao = new FailureBankDao(dataSource);
        FailureInsuranceDao failureInsuranceDao = new FailureInsuranceDao(dataSource);
        FailureBankService bankService = new FailureBankService(failureBankDao,failureInsuranceDao,dataSource);
        bankService.transfer(1111,2222,200.0F);
        assertEquals(800.0F, getBankAmount(1111));
        assertEquals(1200.0F, getInsuranceAmount(2222));
    }

    @Test
    public void transferFailure() throws SQLException {
        FailureBankDao failureBankDao = new FailureBankDao(dataSource);
        FailureInsuranceDao failureInsuranceDao = new FailureInsuranceDao(dataSource);
        FailureBankService bankService = new FailureBankService(failureBankDao,failureInsuranceDao,dataSource);

        int toNonExistId = 3333;
        bankService.transfer(1111,toNonExistId,200.0F);

        assertEquals(1000.0F, getInsuranceAmount(2222));
        assertEquals(800.0F, getBankAmount(1111));
    }

}
