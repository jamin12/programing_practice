package org.jamin.reactive02.section03.class07;

import reactor.core.publisher.Mono;

import java.time.Duration;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;

/**
 * then 기본 개념 예제
 *  - Upstream Mono의 sequence가 종료되면, Mono<Void>를 Downstream으로 전달한다.
 */
public class thenExample01 {
	public static void main(String[] args) {
		Mono
			.just("Hi")
			.delayElement(Duration.ofSeconds(1))
			.doOnNext(Logger::doOnNext)
			.then()
			.subscribe(
				Logger::onNext,
				Logger::onError,
				Logger::onComplete
			);

		TimeUtils.sleep(1500);
	}
}
