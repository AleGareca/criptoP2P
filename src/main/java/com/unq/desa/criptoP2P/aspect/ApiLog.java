package com.unq.desa.criptoP2P.aspect;


import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ApiLog {
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void rescontrollerAnnotation() {
    }

    @Before("rescontrollerAnnotation()")
    public void apiRequestLog(JoinPoint jp) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("------------------------- Aspect Api Log -------------------------");
        StringBuilder log = new StringBuilder(jp.getSignature().getName() + " >>>");
        for (Object arg : jp.getArgs()) {
            log.append("\n   Argumentos: " + arg);
        }
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
    }

    @AfterReturning(pointcut = "rescontrollerAnnotation()",returning = "result")
    public void apiResponseOkLog(JoinPoint jp, Object result){
        var log = "<<< Return << "+ jp.getSignature().getName() + " : " + result;
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);

    }

    @AfterThrowing(pointcut = "rescontrollerAnnotation()", throwing = "exception")
    public void apiResponseExceptionLog(JoinPoint jp, Exception exception) {
        String log = "<<< Return Exception << " + jp.getSignature().getName() + ": " + exception.getClass().getSimpleName() + "->"
                + exception.getMessage();
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
    }
}
