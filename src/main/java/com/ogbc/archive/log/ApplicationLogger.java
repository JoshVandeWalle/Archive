package com.ogbc.archive.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Aspect
@Configuration
@Slf4j
public class ApplicationLogger
{
    /**
     * This advice method execute before entering a controller, business service, or data service method
     * @param joinPoint the execution instance of the advice for the pointcut
     */
    @Before("execution(* com.ogbc.archive.api.*.*(..)) || execution(* com.ogbc.archive.service.*.*(..)) || execution(* com.ogbc.archive.data.repository.*.*(..))")
    public void before(JoinPoint joinPoint)
    {
        // read method signature for inclusion in logs
        String signature = joinPoint.getSignature().toString();
        // log method entry
        log.info("Entering " + signature.substring(signature.indexOf(" ") + 1));
    }

    /**
     * This advice method execute after a return statement in a controller, business service, or data service method
     * @param joinPoint the execution instance of the advice for the pointcut
     * @param result the return value
     */
    @AfterReturning(value = "execution(* com.ogbc.archive.api.*.*(..)) || execution(* com.ogbc.archive.service.*.*(..)) || execution(* com.ogbc.archive.data.repository.*.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result)
    {
        // read method signature for inclusion in logs
        String signature = joinPoint.getSignature().toString();

        // if the method return value is not null
        if ((result instanceof ResponseEntity && ((ResponseEntity<?>) result).getStatusCode().is2xxSuccessful()) || (result instanceof List && !((List<?>) result).isEmpty()))
        {
            // log the happy path execution
            log.info("Exiting " + signature.substring(signature.indexOf(" ") + 1) + " after successful execution");
        }

        else if (result == null)
        {
            // log the null path execution
            log.warn("Exiting " + signature.substring(signature.indexOf(" ") + 1) + " with null");
        }

        else if (result instanceof ResponseEntity)
        {
            // log the null path execution
            log.warn("Exiting " + signature.substring(signature.indexOf(" ") + 1) + " with HTTP Status Code " + ((ResponseEntity<?>) result).getStatusCode());
        }

        else if (result instanceof List)
        {
            // log the null path execution
            log.warn("Exiting " + signature.substring(signature.indexOf(" ") + 1) + " with empty List");
        }

        else
        {
            // log the null return
            log.warn("Exiting " + signature.substring(signature.indexOf(" ") + 1) + " after failure");
        }
    }

    /**
     * This advice method execute after an exception is thrown in a controller, business service, or data service method
     * @param joinPoint joinPoint the execution instance of the advice for the pointcut
     * @param e the exception thrown
     */
    @AfterThrowing(value = "execution(* com.ogbc.archive.api.*.*(..)) || execution(* com.ogbc.archive.service.*.*(..)) || execution(* com.ogbc.archive.data.repository.*.*(..))", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e)
    {
        // read method signature for inclusion in logs
        String signature = joinPoint.getSignature().toString();
        //log the exception
        log.error("Exiting " + signature.substring(signature.indexOf(" ") + 1) + " with exception of type: " + e.getClass() + " and message: " + e.getMessage());
    }
}
