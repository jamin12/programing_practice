package org.jamin.reactive01.section6;

import org.reactivestreams.Subscription;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * Subscriber가 처리 가능한 만큼의 request 갯수를 조절하는 Backpressure 예제
 */
@Slf4j
public class BackpressureExample02 {
	public static int count = 0;

	public static void main(String[] args) {
		Flux.range(1, 5)
			.doOnNext(data -> log.info("do on next data {}", data))
			.doOnRequest(data -> log.info("do on request data {}", data))
			.subscribe(new BaseSubscriber<>() {
				@Override
				protected void hookOnSubscribe(Subscription subscription) {
					request(2);
				}

				@SneakyThrows
				@Override
				protected void hookOnNext(Integer value) {
					count++;
					log.info("on next value {}", value);
					if (count == 2) {
						Thread.sleep(1000);
						request(2);
						count = 0;
					}
				}
			});

	}
}
