package Mrchenli.step7_testioc;


import io.mrchenli.aop.aopserial.aop.interceptor.Before;

import java.lang.reflect.Method;

public class LogBefore implements Before {
    @Override
    public void before(Method method, Object[] objects) {
        System.out.println("在方法调用之前记录日志.......");
    }
}
