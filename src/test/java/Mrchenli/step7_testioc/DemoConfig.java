package Mrchenli.step7_testioc;

import io.mrchenli.aop.aopserial.config.SpringConfig;

import java.util.ArrayList;
import java.util.List;

public class DemoConfig implements SpringConfig {
    @Override
    public String[] basePath() {
        return new String[]{"Mrchenli.myspring"};
    }

    @Override
    public List<Class<?>> getTargetToProxy() {
        List<Class<?>> list = new ArrayList<>();
        list.add(null);
        return list;
    }
}
