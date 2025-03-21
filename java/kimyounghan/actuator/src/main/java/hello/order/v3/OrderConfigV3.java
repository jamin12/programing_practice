package hello.order.v3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.order.OrderService;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class OrderConfigV3 {
	@Bean
	public OrderService orderServiceV3(MeterRegistry registry) {
		return new OrderServiceV3(registry);
	}
}
