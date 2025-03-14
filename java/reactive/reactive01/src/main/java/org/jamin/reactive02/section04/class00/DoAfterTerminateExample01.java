package org.jamin.reactive02.section04.class00;

import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * doOnTerminate()와 doAfterTerminate()의 차이점을 이해하기 위한 예제
 *  - doOnTerminate()는 upstream operator가 종료할 때 호출된다.
 *  - doAfterTerminate는 전체 Sequence가 종료할 때 Downstream에서 Upstream으로 전파(propagation)되면서 호출된다.
 */
public class DoAfterTerminateExample01 {
	public static void main(String[] args) {
		Flux
			.just("HI", "HELLO")
			.filter(data -> data.equals("HI"))
			.doOnTerminate(() -> Logger.doOnTerminate("filter"))
			.doAfterTerminate(() -> Logger.doAfterTerminate("filter"))
			.map(String::toLowerCase)
			.doOnTerminate(() -> Logger.doOnTerminate("map"))
			.doAfterTerminate(() -> Logger.doAfterTerminate("map"))
			.subscribe(
				Logger::onNext,
				error -> {
				},
				Logger::doOnComplete);
	}
}
