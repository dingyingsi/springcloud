package com.dys.springcloud.aop;

import com.dys.springcloud.annotation.OperationLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
public class LogAspects {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut(value = "@annotation(com.dys.springcloud.annotation.OperationLog)")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void logStart(JoinPoint joinPoint) {


    }

    @AfterReturning(value = "pointCut()", returning = "object")
    public void logReturn(JoinPoint joinPoint, Object object) {
        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String contextPath = httpRequest.getContextPath();
        String servletPath = httpRequest.getServletPath();
        String httpMethod = httpRequest.getMethod();
        String ip = httpRequest.getHeader("x_forward_for");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();

        OperationLog operationLog = method.getAnnotation(OperationLog.class);
        String description = operationLog.value();

        Object[] args = joinPoint.getArgs();

        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations == null || parameterAnnotations.length == 0 || (parameterAnnotations.length == 1 && parameterAnnotations[0].length == 0)) {
            // 参数上无注解情况
            System.out.println("请求参数无注解，不处理");
        } else {

            EvaluationContext ctx = new StandardEvaluationContext();
            for (Annotation[] parameterAnnotation : parameterAnnotations) {
                for (Annotation annotation : parameterAnnotation) {
                    if (annotation instanceof RequestBody) {
                        RequestBody RequestBody = (RequestBody) annotation;

                    } else if (annotation instanceof RequestParam) {
                        RequestParam requestParam = (RequestParam)annotation;
                        String name = requestParam.name();
                        name = name == null ? requestParam.value() : "";

                        Object param = args[0];
                        if (param instanceof String || param instanceof Number) {
                            ctx.setVariable(name, param);
                        } else {

                        }

                    } else if (annotation instanceof PathVariable) {
                        PathVariable pathVariable = (PathVariable)annotation;
                        String value = pathVariable.value();
                    }
                }
            }
        }

    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        System.out.println("exception");
    }

    @After(value = "pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("divide end");
    }
}
