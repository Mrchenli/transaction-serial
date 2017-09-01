package Mrchenli.step7_testioc;

import Mrchenli.BankFixture;
import Mrchenli.myspring.demo.controller.TransferController;
import io.mrchenli.aop.aopserial.aop.core.AopProxyFactory;
import io.mrchenli.aop.aopserial.ioc.core.AnnotationContext;
import io.mrchenli.aop.aopserial.ioc.core.BeanFactory;
import org.junit.Test;

public class MySpringIocTest extends BankFixture {

    @Test
    public void testIoc(){

        BeanFactory bf = new AnnotationContext(new DemoConfig());

        TransferController transferController = (TransferController) bf.getBean("transferController");

        String result = transferController.transfer(1111,2222,200.0F);

        System.out.println(result);
    }

    @Test
    public void testAop() throws Exception{

        AopProxyFactory proxy = new AopProxyFactory();

        proxy.setTarget(new AopDemo());

        proxy.addAdvice(new LogBefore());

        IAopDemo p  = (IAopDemo) proxy.getProxy();

        p.doSomething();
    }



}
