package org.jamin.reactive01.section8.class02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.single()을 적용 후,
 * 첫번째 Schedulers.single()에서 할당 된 쓰레드를 재사용 한다.
 */
@Slf4j
public class SchedulersSingleExample02 {
	public static void main(String[] args) throws InterruptedException {

		doTask("task1")
			.subscribe(data -> log.info("data {}", data));

		doTask("task2")
			.subscribe(data -> log.info("data {}", data));

		Thread.sleep(200L);
	}

	private static Flux<Integer> doTask(String taskName) {
		return Flux.fromArray(new Integer[] {1, 3, 5, 7})
			.doOnNext(data -> log.info("fromArray {} {}", taskName, data))
			.publishOn(Schedulers.newSingle("new-single", true))
			.filter(data -> data > 3)
			.doOnNext(data -> log.info("filter {} {}", taskName, data))
			.map(data -> data * 10)
			.doOnNext(data -> log.info("map {} {}", taskName, data));
	}
}