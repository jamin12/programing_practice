package org.jamin.reactive02.section03.class06;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;
import org.reactivestreams.Publisher;

import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * and 활용 예제
 *  - 2 개의 task가 모두 끝났을 때, Complete Signal을 전달해서 추가 task를 수행하는 예제
 */
public class AndExample02 {
	public static void main(String[] args) {
		restartApplicationServer()
			.and(restartDBServer())
			.subscribe(
				Logger::onNext,
				Logger::onError,
				() -> Logger.onComplete("Send an email to Administrator: " +
										"All Servers are restarted successfully")
			);

		TimeUtils.sleep(6000L);
	}

	private static Mono<String> restartApplicationServer() {
		return Mono
			.just("Application Server was restarted successfully.")
			.delayElement(Duration.ofSeconds(2))
			.doOnNext(Logger::doOnNext);
	}

	private static Publisher<String> restartDBServer() {
		return Mono
			.just("DB Server was restarted successfully.")
			.delayElement(Duration.ofSeconds(4))
			.doOnNext(Logger::doOnNext);
	}
}
