package org.jamin.reactive02.section03.class06;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;

/**
 * when 기본 개념 예제
 *  - 파라미터로 입력된 Publisher들이 종료할 때 까지 대기한 후, Mono<Void>를 반환한다.
 */
public class WhenExample01 {
	public static void main(String[] args) {
		Mono
			.when(
				Flux
					.just("Hi", "Tom")
					.delayElements(Duration.ofSeconds(2))
					.doOnNext(Logger::doOnNext),
				Flux
					.just("Hello", "David")
					.delayElements(Duration.ofSeconds(1))
					.doOnNext(Logger::doOnNext)
			)
			.subscribe(
				Logger::onNext,
				Logger::onError,
				Logger::onComplete
			);

		TimeUtils.sleep(5000);
	}
}
