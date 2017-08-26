package Mrchenli;

import Mrchenli.dao.BankDao;
import Mrchenli.dao.InsuranceDao;
import Mrchenli.service.BankService;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public abstract class BaseTest extends BankFixture{

    protected BankService bankService;

    public abstract void initBankService();

    public abstract void transferSuccessTest() throws SQLException;

    public abstract void transferFailureTest() throws SQLException;


    public void transferSuccess() throws SQLException {
        initBankService();
        bankService.transfer(1111,2222,200.0F);
        assertEquals(800.0F, getBankAmount(1111));
        assertEquals(1200.0F, getInsuranceAmount(2222));
    }


    public void transferFailure() throws SQLException {
        initBankService();
        int toNonExistId = 3333;
        bankService.transfer(1111,toNonExistId,200.0F);
        assertEquals(1000.0F, getInsuranceAmount(2222));
        assertEquals(1000.0F, getBankAmount(1111));
    }


}
