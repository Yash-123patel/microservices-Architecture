package com.tekworks.microservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopLogging {

    @After("execution(* com.tekworks.microservice.service.*.*(..))")
    public void printLog(JoinPoint joinPoint) {
        System.out.println("Service Method executed: " + joinPoint.getSignature());
        System.out.println("Target Object: " + joinPoint.getTarget());
    }
}
