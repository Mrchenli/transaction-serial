package Mrchenli.step4_transaction_template;

import Mrchenli.BaseTest;
import Mrchenli.service.step4_transaction_template.TransactionTemplateBankService;
import org.junit.Test;

import java.sql.SQLException;

public class TransactionTemplateBankServiceTest extends BaseTest {
    @Override
    public void initBankService() {
        bankService = new TransactionTemplateBankService(dataSource);
    }

    @Test
    public void transferSuccessTest() throws SQLException {
        transferSuccess();
    }

    @Test
    public void transferFailureTest() throws SQLException {
        transferFailure();
    }
}
