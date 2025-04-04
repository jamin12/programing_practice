package hello.advanced.app.v4;

import org.springframework.stereotype.Repository;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
	private final LogTrace trace;

	public void save(String itemId) {
		AbstractTemplate<Void> abstractTemplate = new AbstractTemplate<>(trace) {
			@Override
			protected Void call() {
				// 저장 로직
				if (itemId.equals("ex")) {
					throw new IllegalArgumentException("예외 발생");
				}
				sleep(1000);
				return null;
			}
		};

		abstractTemplate.execute("OrderRepository.save()");
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
