package hello.advanced.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
	private final OrderServiceV1 orderService;
	private final HelloTraceV1 trace;

	@GetMapping("/v1/request")
	public String request(String itemId) {
		TraceStatus status = trace.begin("OrderController.request()");
		try {
			orderService.orderItem(itemId);
			trace.end(status);
			return "ok";

		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}
}
