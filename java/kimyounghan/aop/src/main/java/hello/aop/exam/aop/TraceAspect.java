package hello.aop.exam.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class TraceAspect {
	@Before("@annotation(hello.aop.exam.annotation.Trace)")
	public void doTrace(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		log.info("Trace: {} args: {}", joinPoint.getSignature(),args);
    }
}
