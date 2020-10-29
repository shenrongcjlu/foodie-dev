package com.imooc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServiceLogAspect {


    @Before("execution(* com.imooc.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("=========开始执行{}.{}=========",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());
        long begin = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        long time = end - begin;
        if (time > 3000) {
            log.error("=======执行结束，耗时{}毫秒======", time);
        }
        log.info("=======执行结束，耗时{}毫秒======", time);
        return result;
    }
}
