package com.unq.desa.criptoP2P.aspect;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodLog {
    @Pointcut("execution(* save (..))")
    public void allMethodSave(){

    }

    @Before("allMethodSave()")
    public void saveLogs(JoinPoint jp){
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info("------------------------- Aspect Method -------------------------");
        StringBuilder log = new StringBuilder(jp.getSignature().getName() + " >>>");
        for (Object arg : jp.getArgs()) {
            log.append(" The repository is saving objects of type ::: " + arg);
        }
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
    }
}
