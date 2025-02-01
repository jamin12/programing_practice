package org.jamin.reactive01.section6;

import org.reactivestreams.Subscription;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * Subscriber가 처리 가능한 만큼의 request 개수를 조절하는 Backpressure 예제
 */
@Slf4j
public class BackpressureExample01 {
	public static void main(String[] args) {
		Flux.range(1, 5)
			.doOnNext(data -> log.info("do on next data {}", data))
			.doOnRequest(data -> log.info("do on request data {}", data))
			.subscribe(new BaseSubscriber<>() {
				@Override
				protected void hookOnSubscribe(Subscription subscription) {
					request(1);
				}

				@SneakyThrows
				@Override
				protected void hookOnNext(Integer value) {
					Thread.sleep(1000);
					log.info("on next value {}", value);
					request(1);
				}
			});

	}
}
