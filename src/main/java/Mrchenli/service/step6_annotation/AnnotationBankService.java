package Mrchenli.service.step6_annotation;

import Mrchenli.dao.BankDao;
import Mrchenli.dao.InsuranceDao;
import Mrchenli.dao.step3_connection_holder.ConnectionHolderBankDao;
import Mrchenli.dao.step3_connection_holder.ConnectionHolderInsuranceDao;
import Mrchenli.dao.step6_annotation.Transactional;
import Mrchenli.service.AbstractService;
import Mrchenli.service.BankService;

import javax.sql.DataSource;

public class AnnotationBankService extends AbstractService implements BankService{

    public AnnotationBankService(DataSource dataSource) {
        super(new ConnectionHolderBankDao(dataSource),
                new ConnectionHolderInsuranceDao(dataSource));
    }

    @Transactional
    public void transfer(int fromId, int toId, float amount) {
        try {
            bankDao.withdraw(fromId,amount);
            insuranceDao.deposit(toId,amount);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
