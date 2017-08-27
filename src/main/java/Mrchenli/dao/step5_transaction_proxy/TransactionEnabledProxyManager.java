package Mrchenli.dao.step5_transaction_proxy;

import Mrchenli.dao.step3_connection_holder.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionEnabledProxyManager{

    private TransactionManager transactionManager;

    public TransactionEnabledProxyManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * 依赖只要目标对象target 和handler 以及做事的transactionManager就可以
     * @param target
     * @return
     */
    public Object proxyFor(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new TransactionInvocationHandler(target, transactionManager));
    }
}

class TransactionInvocationHandler implements InvocationHandler{

    private Object target;
    private TransactionManager transactionManager;

    public TransactionInvocationHandler(Object proxy, TransactionManager transactionManager) {
        this.target = proxy;
        this.transactionManager = transactionManager;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] params) throws Throwable {
        transactionManager.start();
        Object result = null;
        try{
            result = method.invoke(target,params);
            transactionManager.commit();
        }catch (Exception e){
            transactionManager.rollback();
        }finally {
            transactionManager.close();
        }
        return result;
    }
}