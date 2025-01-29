package org.jamin.reactive01.section4;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * Flux 에서의 Operator 체인 사용 예제
 */
@Slf4j
public class FluxExample02 {
	public static void main(String[] args) {
		Flux.fromArray(new Integer[] {6, 9, 13,11})
			.filter(num -> num > 6)
			.map(num -> num % 2)
			.subscribe(remainder -> log.info(String.valueOf(remainder)));
	}
}
