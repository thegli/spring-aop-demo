package ch.thegli.spring.aop.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class AllPublicMethodsTracingLogger {
    private static final Logger LOG = Logger.getLogger(AllPublicMethodsTracingLogger.class.getName());

    @Around("within(@ch.thegli.spring.aop.logging.AllPublicMethodsTraced *) && execution(public * *(..))")
    public Object logAround(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object value = null;

        String typeName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] arguments = proceedingJoinPoint.getArgs();

        try {
            LOG.entering(typeName, methodName, arguments);
            value = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            LOG.exiting(typeName, methodName, value);
        }

        return value;
    }
}
