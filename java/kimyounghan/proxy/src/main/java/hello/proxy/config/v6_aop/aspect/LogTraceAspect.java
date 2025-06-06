package hello.proxy.config.v6_aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class LogTraceAspect {
	private final LogTrace trace;

	public LogTraceAspect(LogTrace trace) {
		this.trace = trace;
	}

	@Around("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		TraceStatus status = null;
		try {
			String message = joinPoint.getSignature().toShortString();
			status = trace.begin(message);
			//target 호출
			Object result = joinPoint.proceed();
			trace.end(status);
			return result;
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}
}
