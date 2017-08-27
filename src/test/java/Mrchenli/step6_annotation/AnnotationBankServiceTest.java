package Mrchenli.step6_annotation;

import Mrchenli.BaseTest;
import Mrchenli.dao.step3_connection_holder.TransactionManager;
import Mrchenli.dao.step6_annotation.TransactionEnabledAnnotationProxy;
import Mrchenli.service.BankService;
import Mrchenli.service.step6_annotation.AnnotationBankService;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class AnnotationBankServiceTest extends BaseTest {
    @Override
    public void initBankService() {
        bankService = new AnnotationBankService(dataSource);
    }

    @Test
    public void transferSuccessTest() throws SQLException {
        initBankService();
        TransactionEnabledAnnotationProxy proxyManager =
                new TransactionEnabledAnnotationProxy(new TransactionManager(dataSource));
        BankService proxyService = (BankService) proxyManager.proxyFor(bankService);
        proxyService.transfer(1111,2222,200.0F);
        assertEquals(800.0F, getBankAmount(1111));
        assertEquals(1200.0F, getInsuranceAmount(2222));
    }

    @Test
    public void transferFailureTest() throws SQLException {
        initBankService();
        TransactionEnabledAnnotationProxy proxyManager =
                new TransactionEnabledAnnotationProxy(new TransactionManager(dataSource));
        BankService proxyService = (BankService) proxyManager.proxyFor(bankService);
        proxyService.transfer(1111,3333,200.0F);
        assertEquals(1000.0F, getBankAmount(1111));
        assertEquals(1000.0F, getInsuranceAmount(2222));
    }
}
