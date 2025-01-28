package hello.order.gauge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
public class StockConfigV1 {

	@Bean
	public MyStockMetric myStockMetric(OrderService orderService, MeterRegistry meterRegistry) {
		return new MyStockMetric(orderService, meterRegistry);
	}

	@Slf4j
	@RequiredArgsConstructor
	static class MyStockMetric {
		private final OrderService orderService;
		private final MeterRegistry registry;

		@PostConstruct
		public void init() {
			Gauge.builder("my.stock", orderService, service -> {
				log.info("stock gauge call");
				return service.getStock().get();
			}).register(registry);
		}
	}
}
