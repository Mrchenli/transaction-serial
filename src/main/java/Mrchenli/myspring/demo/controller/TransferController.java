package Mrchenli.myspring.demo.controller;


import Mrchenli.myspring.demo.transactionpackage.TransactionAspect;
import Mrchenli.service.BankService;
import io.mrchenli.aop.aopserial.aop.core.AopProxyFactory;
import io.mrchenli.aop.aopserial.ioc.annotation.Component;
import io.mrchenli.aop.aopserial.ioc.annotation.Inject;

@Component("transferController")
public class TransferController {

    @Inject(name = "transferService")
    private BankService transferServicce;

    @Inject(name = "transactionAspect")
    private TransactionAspect transactionAspect;

    public String transfer(int fromId,int toId,float amount){

        AopProxyFactory aopProxyFactory = new AopProxyFactory();

        aopProxyFactory.setTarget(transferServicce);
        aopProxyFactory.addAdvice(transactionAspect);
        BankService bankService = (BankService) aopProxyFactory.getProxy();

        bankService.transfer(fromId,toId,amount);
        return "success";
    }

}
