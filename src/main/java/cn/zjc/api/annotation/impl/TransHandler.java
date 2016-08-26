package cn.zjc.api.annotation.impl;

import cn.zjc.api.utils.SpringContextUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author zhangjinci
 * @version 2016/8/26 12:08
 * @function 事务注解处理类
 */
@Component
@Aspect
public class TransHandler {

    private static final Logger log = LoggerFactory.getLogger(TransHandler.class);

    @Pointcut("@within(cn.zjc.api.annotation.Trans)")
    public static void classPointCut() {
    }

    @Pointcut("@annotation(cn.zjc.api.annotation.Trans)")
    public static void methodPointCut() {
    }

    @Around("TransHandler.classPointCut() || methodPointCut()")
    public Object TransAccess(ProceedingJoinPoint pjp) throws Throwable {


        System.out.println("pjp的简短信息:" + pjp.toShortString()); //切点的简短信息
        System.out.println("pjp的详细信息:" + pjp.toLongString());  //切点的详细信息
        System.out.println("参数数组:" + Arrays.toString(pjp.getArgs()));
        System.out.println("AOP代理对象:" + pjp.getThis());
        System.out.println("目标对象:" + pjp.getTarget());
        System.out.println("连接点的签名:" + pjp.getSignature());

        System.out.println("连接点方法在类文件中的位置:" + pjp.getSourceLocation());
        System.out.println("连接点类型:" + pjp.getKind());
        System.out.println("连接点的静态部分:" + pjp.getStaticPart());

        //获取连接点方法对象
        Method m  = ((MethodSignature)pjp.getSignature()).getMethod();

        //代理目标对象(源类)
        Class<?> clazz = pjp.getTarget().getClass();

        System.out.println("连接点方法名:" + m.getName());
        System.out.println("被代理类对象名:" + clazz.getName());
        return pjp.proceed();





    }
}
