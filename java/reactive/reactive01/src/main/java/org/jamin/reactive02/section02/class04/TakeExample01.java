package org.jamin.reactive02.section02.class04;

import java.time.Duration;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;

import reactor.core.publisher.Flux;

/**
 * take 기본 개념 예제
 *  - 파라미터로 입력한 숫자만큼만 Downstream으로 emit 한다.
 */
public class TakeExample01 {
	public static void main(String[] args) {
		Flux
			.interval(Duration.ofSeconds(1))
			.doOnNext(Logger::doOnNext)
			.take(3)
			.subscribe(Logger::onNext);

		TimeUtils.sleep(5000L);
	}
}