package hello.aop.order.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
	@Pointcut("execution(* hello.aop.order..*(..))")
	public void allOrder() {
	}

	// 클래스 이름 패턴이 *Service
	@Pointcut("execution(* *..*Service.*(..))")
	public void allService() {
	}

	@Pointcut("allOrder() && allService()s")
	public void orderAndService() {
	}
}
