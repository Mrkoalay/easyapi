package cn.easyapi.rest.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class PointGuardAspect {
    public void pointGuard(){}

    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

    public void doBefore(JoinPoint joinPoint) throws RuntimeException {
        LocalDateTime datetime = LocalDateTime.now();
    }
}
