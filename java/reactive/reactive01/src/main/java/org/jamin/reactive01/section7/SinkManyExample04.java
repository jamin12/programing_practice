package org.jamin.reactive01.section7;

import static reactor.core.publisher.Sinks.EmitFailureHandler.*;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * Sinks.Many 예제
 *  - replay()를 사용하여 이미 emit된 데이터 중에서 특정 개수의 최신 데이터만 전달하는 예제
 */
@Slf4j
public class SinkManyExample04 {
	public static void main(String[] args) {
		// 구독 이후, emit된 데이터 중에서 최신 데이터 2개만 replay 한다.
		Sinks.Many<Integer> replaySink = Sinks.many().replay().limit(2);
		Flux<Integer> fluxView = replaySink.asFlux();

		replaySink.emitNext(1, FAIL_FAST);
		replaySink.emitNext(2, FAIL_FAST);
		replaySink.emitNext(3, FAIL_FAST);

		fluxView.subscribe(data -> log.info("Subscriber1 {}", data));

		replaySink.emitNext(4, FAIL_FAST);

		fluxView.subscribe(data -> log.info("Subscriber2 {}", data));
	}
}