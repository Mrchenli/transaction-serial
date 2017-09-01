package Mrchenli.myspring.demo.service;

import Mrchenli.dao.BankDao;
import Mrchenli.dao.InsuranceDao;
import Mrchenli.service.BankService;
import io.mrchenli.aop.aopserial.aop.annotation.Aspect;
import io.mrchenli.aop.aopserial.ioc.annotation.Component;
import io.mrchenli.aop.aopserial.ioc.annotation.Inject;


@Component("transferService")
public class TransferServiceImpl implements BankService{

    @Inject(name = "bankDao")
    private BankDao bankDao;
    @Inject(name = "insuranceDao")
    private InsuranceDao insuranceDao;


    public void transfer(int fromId, int toId, float amount) {
        try {
            System.out.println("转账事物开始");
            bankDao.withdraw(fromId,amount);
            insuranceDao.deposit(toId,amount);
            System.out.println("转账业务完成");
        }catch (Exception e){
            throw new RuntimeException();
        }
    }


}
