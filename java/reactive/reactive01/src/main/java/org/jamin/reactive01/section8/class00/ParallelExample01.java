package org.jamin.reactive01.section8.class00;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * parallel()만 사용할 경우에는 병렬로 작업을 수행하지 않는다.
 */
@Slf4j
public class ParallelExample01 {
	public static void main(String[] args) throws InterruptedException {
		Flux.fromArray(new Integer[] {1, 3, 5, 7, 9, 11, 13, 15})
			.parallel()
			.subscribe(data -> log.info("data {}", data));
	}
}