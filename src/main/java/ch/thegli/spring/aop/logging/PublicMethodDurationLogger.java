package ch.thegli.spring.aop.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class PublicMethodDurationLogger {
    private static final Logger LOG = Logger.getLogger(PublicMethodDurationLogger.class.getName());

    @Around("@annotation(ch.thegli.spring.aop.logging.PublicMethodDuration) && execution(public * *(..))")
    public Object duration(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object value;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            long duration = System.currentTimeMillis() - start;
            LOG.info(proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName()
                    + "." + proceedingJoinPoint.getSignature().getName()
                    + " took " + duration + " ms");
        }

        return value;
    }
}
