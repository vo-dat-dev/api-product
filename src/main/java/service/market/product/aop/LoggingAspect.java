package service.market.product.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* service.market.product.controller.*.*(..)) || execution(* service.market.product.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        
        logger.info("Entering method: {}.{} with arguments: {}", 
            className, methodName, Arrays.toString(joinPoint.getArgs()));
        
        try {
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            logger.info("Exiting method: {}.{} with result: {} (took {} ms)", 
                className, methodName, result, (endTime - startTime));
            return result;
        } catch (Throwable e) {
            logger.error("Exception in method: {}.{} with error: {}", 
                className, methodName, e.getMessage());
            throw e;
        }
    }

    @AfterThrowing(pointcut = "execution(* service.market.product.controller.*.*(..)) || execution(* service.market.product.service.*.*(..))", 
                  throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.error("Exception in method: {}.{} with error: {}", 
            className, methodName, ex.getMessage());
    }
} 