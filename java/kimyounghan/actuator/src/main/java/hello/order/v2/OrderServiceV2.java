package hello.order.v2;

import java.util.concurrent.atomic.AtomicInteger;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderServiceV2 implements OrderService {
	private AtomicInteger stock = new AtomicInteger(100);

	@Override
	@Counted("my.order")
	public void order() {
		log.info("주문");
		stock.decrementAndGet();
	}

	@Override
	@Counted("my.order")
	public void cancel() {
		log.info("취소");
		stock.incrementAndGet();
	}

	@Override
	public AtomicInteger getStock() {
		return stock;
	}
}
