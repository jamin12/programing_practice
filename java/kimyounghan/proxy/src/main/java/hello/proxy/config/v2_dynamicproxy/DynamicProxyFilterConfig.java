package hello.proxy.config.v2_dynamicproxy;

import java.lang.reflect.Proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import hello.proxy.trace.logtrace.LogTrace;

@Configuration
public class DynamicProxyFilterConfig {
	private static final String[] PATTERNS = {"request*", "order*", "save*"};

	@Bean
	public OrderControllerV1 orderController(LogTrace logTrace) {
		OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(logTrace));
		LogTraceFilterHandler logTraceBasicHandler = new LogTraceFilterHandler(controllerImpl, logTrace, PATTERNS);
		return (OrderControllerV1)Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
			new Class[] {OrderControllerV1.class}, logTraceBasicHandler);
	}

	@Bean
	public OrderServiceV1 orderService(LogTrace logTrace) {
		OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
		LogTraceFilterHandler logTraceBasicHandler = new LogTraceFilterHandler(serviceImpl, logTrace, PATTERNS);
		return (OrderServiceV1)Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
			new Class[] {OrderServiceV1.class}, logTraceBasicHandler);
	}

	@Bean
	public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
		OrderRepositoryV1Impl repositoryImpl = new OrderRepositoryV1Impl();
		LogTraceFilterHandler logTraceBasicHandler = new LogTraceFilterHandler(repositoryImpl, logTrace, PATTERNS);
		return (OrderRepositoryV1)Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
			new Class[] {OrderRepositoryV1.class}, logTraceBasicHandler);
	}
}
