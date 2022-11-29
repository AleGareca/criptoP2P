package com.unq.desa.criptoP2P.aspect;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodLog {
    @Pointcut("execution(* com.unq.desa.criptoP2P.persistence.*.* (..))")
    public void allMethodRepository(){

    }

    @Before("allMethodRepository()")
    public void saveLogs(JoinPoint jp){
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("------------------------- Aspect Method repository-------------------------");
        StringBuilder log = new StringBuilder(jp.getSignature().getName() + " >>>");
        for (Object arg : jp.getArgs()) {
            log.append(" The repository is saving objects of type ::: " + arg);
        }
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
    }

    @AfterReturning(pointcut = "allMethodRepository()",returning = "result")
    public void apiResponseOkLog(JoinPoint jp, Object result){
        var log = "<<< Return << "+ jp.getSignature().getName() + " : " + result;
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);

    }

    @AfterThrowing(pointcut = "allMethodRepository()", throwing = "exception")
    public void apiResponseExceptionLog(JoinPoint jp, Exception exception) {
        String log = "<<< Return Exception << " + jp.getSignature().getName() + ": " + exception.getClass().getSimpleName() + "->"
                + exception.getMessage();
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
    }

}
