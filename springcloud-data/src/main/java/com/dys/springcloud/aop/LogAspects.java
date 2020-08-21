package com.dys.springcloud.aop;

import com.dys.springcloud.annotation.OperationLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

@Aspect
public class LogAspects {

    private ExpressionParser expressionParser = new SpelExpressionParser();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut(value = "@annotation(com.dys.springcloud.annotation.OperationLog)")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void logStart(JoinPoint joinPoint) {


    }

    @AfterReturning(value = "pointCut()", returning = "object")
    public void logReturn(JoinPoint joinPoint, Object object) throws Exception {
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

        Map<String, Object> nameAndArgs = this.getArgNameAndValue(this.getClass(), className, methodName, joinPoint.getArgs());

        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        standardEvaluationContext.setVariables(nameAndArgs);

        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

        System.out.println(spelExpressionParser.parseExpression(description, new TemplateParserContext()).getValue(standardEvaluationContext));

    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        System.out.println("exception");
    }

    @After(value = "pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("divide end");
    }



    private Map<String,Object> getArgNameAndValue(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException {
        Map<String,Object > map=new HashMap<>();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);
        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            return map;
        }
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++){
            map.put( attr.variableName(i + pos),args[i]);
        }
        return map;
    }
}
