package Mrchenli.step5_transaction_proxy;

import Mrchenli.BaseTest;
import Mrchenli.dao.step3_connection_holder.TransactionManager;
import Mrchenli.dao.step5_transaction_proxy.TransactionEnabledProxyManager;
import Mrchenli.service.BankService;
import Mrchenli.service.step5_transaction_proxy.TransactionProxyBankService;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

/**
 * 这样做就让那个service层和这个datasource完全解耦了 datasource,service依赖转到了 代理manager
 */
public class TransactionProxyBankServiceTest extends BaseTest {

    @Override
    public void initBankService() {
        //controller一般是会传递bankService过来的
        bankService = new TransactionProxyBankService(dataSource);
    }

    @Test
    public void transferSuccessTest() throws SQLException {
        initBankService();
        TransactionEnabledProxyManager proxyManager =
                new TransactionEnabledProxyManager(new TransactionManager(dataSource));
        //这个生成代理时候获取的接口是当前对象的类的这一层的接口
        BankService proxyService = (BankService) proxyManager.proxyFor(bankService);
        proxyService.transfer(1111,2222,200.0F);
        assertEquals(800.0F, getBankAmount(1111));
        assertEquals(1200.0F, getInsuranceAmount(2222));
    }

    @Test
    public void transferFailureTest() throws SQLException {
        initBankService();
        TransactionEnabledProxyManager proxyManager =
                new TransactionEnabledProxyManager(new TransactionManager(dataSource));
        BankService proxyService = (BankService) proxyManager.proxyFor(bankService);
        proxyService.transfer(1111,3333,200.0F);
        assertEquals(1000.0F, getBankAmount(1111));
        assertEquals(1000.0F, getInsuranceAmount(2222));
    }
}
