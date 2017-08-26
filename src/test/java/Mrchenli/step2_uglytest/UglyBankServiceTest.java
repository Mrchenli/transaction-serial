package Mrchenli.step2_uglytest;

import Mrchenli.BankFixture;
import Mrchenli.step2_ugly.UglyBankDao;
import Mrchenli.step2_ugly.UglyBankService;
import Mrchenli.step2_ugly.UglyInsuranceDao;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

public class UglyBankServiceTest extends BankFixture {

    @Test
    public void transferSuccess() throws SQLException {
        UglyBankDao bankDao = new UglyBankDao();
        UglyInsuranceDao insuranceDao = new UglyInsuranceDao();

        UglyBankService bankService= new UglyBankService(dataSource,bankDao,insuranceDao);

        bankService.transfer(1111,2222,200.0F);
        assertEquals(800.0F, getBankAmount(1111));
        assertEquals(1200.0F, getInsuranceAmount(2222));

    }

    @Test
    public void transferFailure() throws SQLException {
        UglyBankDao bankDao = new UglyBankDao();
        UglyInsuranceDao insuranceDao = new UglyInsuranceDao();

        UglyBankService bankService = new UglyBankService(dataSource,bankDao,insuranceDao);

        int toNonExistId = -1;
        bankService.transfer(1111, toNonExistId,200.0F);

        assertEquals(1000.0F,getBankAmount(1111));
        assertEquals(1000.0F, getInsuranceAmount(2222));
    }

}
