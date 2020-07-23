package io.aspect.springbootdemo.aspec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* io.aspect.springbootdemo.web.*.*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore(final JoinPoint joinPoint) {
        final ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        final HttpServletRequest request = attributes.getRequest();
        final String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName();
        final RequestLog log = RequestLog.builder().url(request.getRequestURL().toString())
                .ip(request.getRemoteAddr())
                .classMethod(classMethod)
                .args(joinPoint.getArgs()).build();

        logger.info("----------- do before 1: {} ----------", log);
    }

    @After("log()")
    public void doAfter() throws InterruptedException {
        Thread.sleep(10);
        logger.info("----------- do after 1 ----------");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturning(final Object result) throws InterruptedException {

        Thread.sleep(10);
        logger.info("----------- do after returning {} ----------", result);
    }

    @Data
    @Builder
    private static class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    }

}
