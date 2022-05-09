package fit.wenchao.mybatisCodeGen.springComponent;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


public class ApplicationContextHolder implements org.springframework.context.ApplicationContextAware {

    public ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> T getBeanByType(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    public Object getBeanByName(String name) {
        return applicationContext.getBean(name);
    }

    public <T> T getBeanByNameAndType(Class<T> tClass, String name) {
        return applicationContext.getBean(name, tClass);
    }
}


