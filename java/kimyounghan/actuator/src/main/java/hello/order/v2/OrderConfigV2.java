package hello.order.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.order.OrderService;
import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class OrderConfigV2 {
	@Bean
	public OrderService orderServiceV1() {
		return new OrderServiceV2();
	}

	@Bean
	public CountedAspect countAspect(MeterRegistry registry) {
		return new CountedAspect(registry);
	}
}
