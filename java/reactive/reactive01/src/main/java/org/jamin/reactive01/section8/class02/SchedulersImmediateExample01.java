package org.jamin.reactive01.section8.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.immediate()을 적용하기 전.
 * 2개의 parallel 쓰레드가 할당된다.
 */
@Slf4j
public class SchedulersImmediateExample01 {
	public static void main(String[] args) throws InterruptedException {
		Flux.fromArray(new Integer[] {1, 3, 5, 7})
			.publishOn(Schedulers.parallel())
			.filter(data -> data > 3)
			.doOnNext(data -> log.info("filter {}", data))
			.publishOn(Schedulers.parallel())
			.map(data -> data * 10)
			.doOnNext(data -> log.info("map {}", data))
			.subscribe(result -> log.info("result {}", result));

		Thread.sleep(200L);
	}
}