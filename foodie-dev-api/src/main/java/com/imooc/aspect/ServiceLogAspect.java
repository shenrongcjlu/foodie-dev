package com.imooc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/5/22 20:46
 */
@Slf4j
@Aspect
@Component
public class ServiceLogAspect {

    @Around("execution(* com.imooc.service.portal.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("===========开始执行 {}.{}===========",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName());
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("===========执行结束，耗时{} s===========", (end - begin) / 1000);
        return result;
    }

}
