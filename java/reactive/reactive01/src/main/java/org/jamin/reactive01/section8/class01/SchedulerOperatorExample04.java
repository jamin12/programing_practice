package org.jamin.reactive01.section8.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * subscribeOn()은 구독 직후에 실행 될 쓰레드를 지정한다.
 * 즉, 원본 Publisher의 실행 쓰레드를 subscribeOn()에서 지정한 쓰레드로 바꾼다.
 */
@Slf4j
public class SchedulerOperatorExample04 {
	public static void main(String[] args) throws InterruptedException {
		Flux.fromArray(new Integer[] {1, 3, 5, 7})
			.subscribeOn(Schedulers.boundedElastic())
			.doOnNext(data -> log.info("fromArray {}", data))
			.filter(data -> data > 3)
			.doOnNext(data -> log.info("filter {}", data))
			.map(data -> data * 10)
			.doOnNext(data -> log.info("map {}", data))
			.subscribe(result -> log.info("result {}", result));

		Thread.sleep(500L);
	}
}