package org.jamin.reactive01.section7;

import static reactor.core.publisher.Sinks.EmitFailureHandler.*;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public class SinkManyExample05 {
	public static void main(String[] args) {
		// 구독 시점과 상관없이 emit된 모든 데이터를 replay 한다.
		Sinks.Many<Integer> replaySink = Sinks.many().replay().all();
		Flux<Integer> fluxView = replaySink.asFlux();

		replaySink.emitNext(1, FAIL_FAST);
		replaySink.emitNext(2, FAIL_FAST);
		replaySink.emitNext(3, FAIL_FAST);

		fluxView.subscribe(data -> log.info("Subscriber1 {}", data));
		fluxView.subscribe(data -> log.info("Subscriber2 {}", data));
	}
}