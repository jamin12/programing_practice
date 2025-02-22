package org.jamin.reactive01.section8.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Operator 체인에서 publishOn( )이 호출되면 publishOn( ) 호출 이후의 Operator 체인은
 * 다음 publisherOn( )을 만나기전까지 publishOn( )에서 지정한 Thread에서 실행이 된다.
 */
@Slf4j
public class SchedulerOperatorExample02 {
	public static void main(String[] args) throws InterruptedException {
		Flux.fromArray(new Integer[] {1, 3, 5, 7})
			.doOnNext(data -> log.info("fromArray {}", data))
			.publishOn(Schedulers.parallel())
			.filter(data -> data > 3)
			.doOnNext(data -> log.info("filter {}", data))
			.map(data -> data * 10)
			.doOnNext(data -> log.info("map {}", data))
			.subscribe(result -> log.info("result {}", result));

		Thread.sleep(500L);
	}
}