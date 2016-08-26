package cn.zjc.api.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author zhangjinci
 * @version 2016/8/26 12:14
 * @function
 */
public class SpringContextUtil implements ApplicationContextAware{

    private static ApplicationContext applicationContext; //Spring应用上下文
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
           SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return SpringContextUtil.applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName){
        return (T) applicationContext.getBean(beanName);
    }

    public static <T>T getBean(Class<T> clazz){
        return  applicationContext.getBean(clazz);
    }

}
