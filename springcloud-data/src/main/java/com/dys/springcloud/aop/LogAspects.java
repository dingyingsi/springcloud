package com.dys.springcloud.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LogAspects {
    @Pointcut(value = "execution (* com.dys.springcloud.controller..*.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("divide start");
    }

    @After(value = "pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("divide end");
    }

    @AfterReturning(value = "pointCut()", returning = "object")
    public void logReturn(Object object) {
        System.out.println("divide return");
    }
}
