package org.jamin.reactive01.section8.class01;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * subscribeOn( )과 publishOn( )이 같이 있다면, publishOn( )을 만나기 전 까지의 Upstream Operator 체인은
 * subscribeOn( )에서 지정한 쓰레드에서 실행되고, publishOn( )을 만날때마다
 * publishOn( ) 아래의 Operator 체인 downstream은 publishOn( )에서 지정한 쓰레드에서 실행된다.
 */
@Slf4j
public class SchedulerOperatorExample05 {
	public static void main(String[] args) throws InterruptedException {
		Flux.fromArray(new Integer[] {1, 3, 5, 7})
			.subscribeOn(Schedulers.boundedElastic())
			.filter(data -> data > 3)
			.doOnNext(data -> log.info("filter {}", data))
			.publishOn(Schedulers.parallel())
			.map(data -> data * 10)
			.doOnNext(data -> log.info("map {}", data))
			.subscribe(result -> log.info("result {}", result));

		Thread.sleep(500L);
	}
}