package org.jamin.reactive02.section01.class05;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

/**
 * create 개념 이해 예제
 *  - Subscriber가 request 할 경우에 next signal 이벤트를 발생하는 예제
 *  - Backpressure 전략을 적용하는 예제
 */
public class CreateExample03 {
	public static int start = 1;
	public static int end = 4;

	public static void main(String[] args) {
		Flux.create((FluxSink<Integer> emitter) -> {
				emitter.onRequest(n -> {
					Logger.info("# requested: " + n);
					TimeUtils.sleep(500L);
					for (int i = start; i <= end; i++) {
						emitter.next(i);
					}
					start += 4;
					end += 4;
				});

				emitter.onDispose(() -> {
					Logger.info("# clean up");
				});
			}, FluxSink.OverflowStrategy.DROP)
			.subscribeOn(Schedulers.boundedElastic())
			.publishOn(Schedulers.parallel(), 2)
			.subscribe(Logger::onNext);

		TimeUtils.sleep(3000L);
	}
}