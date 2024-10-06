package hello.aop.exam.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class RetryAspect {
	@Around("@annotation(retry)")
	public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
		log.info("retry: {} retry: {}", joinPoint.getSignature(), retry);
		int value = retry.value();
		Exception exceptionHolder = null;
		for (int i = 1; i <= value; i++) {
			try {
				log.info("retry:  retry count : {} / {}", i, value);
				return joinPoint.proceed();
			} catch (Exception e) {
				exceptionHolder = e;
			}
		}
		throw exceptionHolder;
	}
}
