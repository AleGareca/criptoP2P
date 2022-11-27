package com.unq.desa.criptoP2P.aspect;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class TimeLog {
    @Pointcut("@annotation(com.unq.desa.criptoP2P.aspect.TimeAnnotation)")
    public void timeLog(){
    }


    @Around("timeLog()")
    public Object time(ProceedingJoinPoint pjp) throws Throwable {
        long initTime = new Date().getTime();
        var obj = pjp.proceed();
        LogManager.getLogger(this.getClass()).info("-----> Time Log (" + pjp.getSignature().getDeclaringTypeName() + ":"
                + pjp.getSignature().getName() + "): " + (new Date().getTime() - initTime) + "ms");
        return obj;
    }


}
