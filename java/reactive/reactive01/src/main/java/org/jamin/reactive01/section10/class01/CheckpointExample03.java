package org.jamin.reactive01.section10.class01;

import org.jamin.reactive01.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * checkpoint() Operator 를 2개 사용한 예제
 * - 발생한 에러는 Operator 체인에 전파가 되기때문에 각각의 checkpoint()에서 확인할 수 있다.
 */
public class CheckpointExample03 {
	public static void main(String[] args) {
		Flux.just(2, 4, 6, 8)
			.zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
			.checkpoint()
			.map(num -> num + 2)
			.checkpoint()
			.subscribe(Logger::onNext, Logger::onError);
	}
}