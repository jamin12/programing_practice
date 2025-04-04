package org.jamin.reactive02.section02.class05;

import org.jamin.reactive02.common.SampleData;
import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * takeWhile 기본 예제
 *  - 파라미터로 입력되는 Predicate이 true인 동안 emit 된 데이터만 Downstream에 emit 한다.
 */
public class TakeWhileExample {
	public static void main(String[] args) {
		Flux
			.fromIterable(SampleData.btcTopPricesPerYear)
			.takeWhile(tuple -> tuple.getT2() < 10_000_000)
			.subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
	}
}