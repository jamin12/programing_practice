package org.jamin.reactive02.section03.class02;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * flatMapSequential 기본 개념 예제
 *  - 비동기적으로 동작할 경우에도 emit 되는 순서를 보장한다.
 */
public class FlatMapSequentialExample {
	public static void main(String[] args) {
		Flux
			.range(2, 8)
			.flatMapSequential(dan -> Flux
				.range(1, 9)
				.publishOn(Schedulers.parallel())
				.map(n -> dan + " * " + n + " = " + dan * n))

			.subscribe(Logger::onNext);

		TimeUtils.sleep(200L);
	}
}
