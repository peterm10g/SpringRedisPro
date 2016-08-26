package cn.zjc.api.annotation;

import java.lang.annotation.*;

/**
 * @author zhangjinci
 * @version 2016/8/26 10:21
 * @function @Transation是Spring的事务注解,为了避免重名冲突此注解为Trans
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Trans {
}
