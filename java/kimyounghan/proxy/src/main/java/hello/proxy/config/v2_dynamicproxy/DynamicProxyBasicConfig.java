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
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;

@Configuration
public class DynamicProxyBasicConfig {
	@Bean
	public OrderControllerV1 orderController(LogTrace logTrace) {
		OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(logTrace));
		LogTraceBasicHandler logTraceBasicHandler = new LogTraceBasicHandler(controllerImpl, logTrace);
		return (OrderControllerV1)Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
			new Class[] {OrderControllerV1.class}, logTraceBasicHandler);
	}

	@Bean
	public OrderServiceV1 orderService(LogTrace logTrace) {
		OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
		LogTraceBasicHandler logTraceBasicHandler = new LogTraceBasicHandler(serviceImpl, logTrace);
		return (OrderServiceV1)Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
			new Class[] {OrderServiceV1.class}, logTraceBasicHandler);
	}

	@Bean
	public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
		OrderRepositoryV1Impl repositoryImpl = new OrderRepositoryV1Impl();
		LogTraceBasicHandler logTraceBasicHandler = new LogTraceBasicHandler(repositoryImpl, logTrace);
		return (OrderRepositoryV1)Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
			new Class[] {OrderRepositoryV1.class}, logTraceBasicHandler);
	}
}
