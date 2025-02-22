package org.jamin.reactive01.section6;

import java.time.Duration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Unbounded request 일 경우, Downstream 에 Backpressure Error 전략을 적용하는 예제
 *  - Downstream 으로 전달 할 데이터가 버퍼에 가득 찰 경우, Exception을 발생 시키는 전략
 */
@Slf4j
public class BackpressureStrategyErrorExample {
	public static void main(String[] args) throws InterruptedException {
		Flux
			.interval(Duration.ofMillis(1L))
			.onBackpressureError()
			.doOnNext(data -> log.info("do on next data {}", data))
			.publishOn(Schedulers.parallel())
			.subscribe(data -> {
					try {
						Thread.sleep(5L);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					log.info("on next data {}", data);
				},
				error -> log.error("error {}", error));

		Thread.sleep(2000L);
	}
}