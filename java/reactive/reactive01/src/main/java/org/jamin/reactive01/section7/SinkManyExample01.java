package org.jamin.reactive01.section7;

import static reactor.core.publisher.Sinks.EmitFailureHandler.*;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * Sinks.Many 예제
 *  - unicast()를 사용해서 단 하나의 Subscriber에게만 데이터를 emit하는 예제
 */
@Slf4j
public class SinkManyExample01 {
	public static void main(String[] args) {
		// 단 하나의 Subscriber에게만 데이터를 emit할 수 있다.
		Sinks.Many<Integer> unicastSink = Sinks.many().unicast().onBackpressureBuffer();
		Flux<Integer> fluxView = unicastSink.asFlux();

		unicastSink.emitNext(1, FAIL_FAST);
		unicastSink.emitNext(2, FAIL_FAST);

		fluxView.subscribe(data -> log.info("Subscriber1 {}", data));

		unicastSink.emitNext(3, FAIL_FAST);

		// TODO 주석 전, 후 비교해서 보여 줄 것.
		// fluxView.subscribe(data -> log.info("Subscriber2 {}", data));
	}
}