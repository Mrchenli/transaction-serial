package Mrchenli.service;

import Mrchenli.dao.BankDao;
import Mrchenli.dao.InsuranceDao;

import javax.sql.DataSource;

public abstract class AbstractService implements BankService {

    protected BankDao bankDao;
    protected InsuranceDao insuranceDao;


    public AbstractService(BankDao bankDao, InsuranceDao insuranceDao) {
        this.bankDao = bankDao;
        this.insuranceDao = insuranceDao;
    }

}
