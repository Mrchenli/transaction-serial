package Mrchenli.step2_uglytest;

import Mrchenli.BaseTest;
import Mrchenli.service.step2_ugly.UglyBankServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

public class UglyBankServiceTest extends BaseTest {

    @Override
    public void initBankService() {
        bankService =  new UglyBankServiceImpl(dataSource);
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
