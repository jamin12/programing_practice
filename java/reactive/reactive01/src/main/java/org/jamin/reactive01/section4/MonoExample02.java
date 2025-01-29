package org.jamin.reactive01.section4;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Mono 기본 개념 예제
 *  원본 데이터의 emit 없이 onComplete signal 만 emit 한다.
 */
@Slf4j
public class MonoExample02 {
	public static void main(String[] args) {
		Mono.empty()
			.subscribe(
				data -> log.info("Received: {}", data),
				error -> log.error("Error: {}", error.getMessage()),
				() -> log.info("Completed")
			);
	}
}
