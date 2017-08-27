package Mrchenli.dao.step6_annotation;

import Mrchenli.dao.step3_connection_holder.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionEnabledAnnotationProxy {

    private TransactionManager transactionManager;

    public TransactionEnabledAnnotationProxy(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Object proxyFor(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new AnnotationTransactionInvocationHandler(target,transactionManager));
    }

}

class AnnotationTransactionInvocationHandler implements InvocationHandler{

    private Object target;
    private TransactionManager transactionManager;

    public AnnotationTransactionInvocationHandler(Object target, TransactionManager transactionManager) {
        this.target = target;
        this.transactionManager = transactionManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
        Method originalMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        if(!originalMethod.isAnnotationPresent(Transactional.class)){
            return method.invoke(target,params);
        }
        transactionManager.start();
        Object result = null;
        try {
            result = method.invoke(target, params);
            transactionManager.commit();
            System.out.println();
        }catch (Exception e) {
            transactionManager.rollback();
        } finally {
            transactionManager.close();
        }
        return result;
    }
}
