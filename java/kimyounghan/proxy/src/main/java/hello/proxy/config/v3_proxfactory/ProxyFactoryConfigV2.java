package hello.proxy.config.v3_proxfactory;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v3_proxfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {

	@Bean
	public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
		OrderControllerV2 orderControllerV2 = new OrderControllerV2(orderServiceV2(logTrace));
		ProxyFactory proxyFactory = new ProxyFactory(orderControllerV2);
		proxyFactory.addAdvisor(getAdvisor(logTrace));
		OrderControllerV2 proxy = (OrderControllerV2)proxyFactory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(),
			orderControllerV2.getClass());
		return proxy;
	}

	@Bean
	public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
		OrderServiceV2 orderServiceV2 = new OrderServiceV2(orderRepositoryV2(logTrace));
		ProxyFactory proxyFactory = new ProxyFactory(orderServiceV2);
		proxyFactory.addAdvisor(getAdvisor(logTrace));
		OrderServiceV2 proxy = (OrderServiceV2)proxyFactory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(),
			orderServiceV2.getClass());
		return proxy;
	}

	@Bean
	public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
		OrderRepositoryV2 orderRepositoryV2 = new OrderRepositoryV2();
		ProxyFactory proxyFactory = new ProxyFactory(orderRepositoryV2);
		proxyFactory.addAdvisor(getAdvisor(logTrace));
		OrderRepositoryV2 proxy = (OrderRepositoryV2)proxyFactory.getProxy();
		log.info("ProxyFactory proxy={}, target={}", proxy.getClass(),
			orderRepositoryV2.getClass());
		return proxy;
	}

	private Advisor getAdvisor(LogTrace logTrace) {
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedNames("request*", "order*", "save*");
		LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);

		return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
	}
}
