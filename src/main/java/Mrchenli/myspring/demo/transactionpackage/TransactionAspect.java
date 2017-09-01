package Mrchenli.myspring.demo.transactionpackage;

import io.mrchenli.aop.aopserial.aop.annotation.Match;
import io.mrchenli.aop.aopserial.aop.interceptor.After;
import io.mrchenli.aop.aopserial.aop.interceptor.Before;
import io.mrchenli.aop.aopserial.aop.interceptor.End;
import io.mrchenli.aop.aopserial.aop.interceptor.Error;
import io.mrchenli.aop.aopserial.ioc.annotation.Component;
import io.mrchenli.aop.aopserial.ioc.annotation.Inject;

import java.lang.reflect.Method;
import java.sql.SQLException;

@Component("transactionAspect")
@Match(methodMatch = "Mrchenli.myspring.demo.service.TransferServiceImpl.transfer")
public class TransactionAspect implements Before,End,After,Error {

    @Inject(name = "transactionManager")
    TransactionManager transactionManager;

    @Override
    public void before(Method method, Object[] objects) {
        System.out.println("开启事物");
        try {
            transactionManager.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void error(Method method, Object[] objects, Exception e) {
        System.out.println("事物回滚");
        transactionManager.rollback();
    }

    @Override
    public void after(Method method, Object[] objects, Object o) {
        System.out.println("提交事物");
        try {
            transactionManager.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void end(Method method, Object[] objects, Object o) {
        System.out.println("关闭事物");
        transactionManager.close();
    }
}
