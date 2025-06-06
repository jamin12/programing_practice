package hello.advanced.app.v2;

import org.springframework.stereotype.Service;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
	private final OrderRepositoryV2 orderRepository;
	private final HelloTraceV2 trace;

	public void orderItem(TraceId traceId, String itemId) {
		TraceStatus status = trace.beginSync(traceId, "OrderService.orderItem()");
		try {
			orderRepository.save(status.getTraceId(), itemId);
			trace.end(status);

		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}
}
