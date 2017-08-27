package Mrchenli.step3_connection_holder;

import Mrchenli.BaseTest;
import Mrchenli.service.step3_connection_holder.ConnectionHolderBankService;
import org.junit.Test;

import java.sql.SQLException;

public class ConnectionHolderBankServiceTest extends BaseTest {


    @Override
    public void initBankService() {
        bankService = new ConnectionHolderBankService(dataSource);
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
