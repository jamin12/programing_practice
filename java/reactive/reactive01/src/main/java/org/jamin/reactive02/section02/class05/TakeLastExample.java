package org.jamin.reactive02.section02.class05;

import org.jamin.reactive02.common.SampleData;
import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * takeLast 기본 예제
 *  - emit 된 데이터 중에서 파라미터로 입력된 갯수만큼 가장 마지막에 emit 된 데이터만 emit 한다.
 */
public class TakeLastExample {
	public static void main(String[] args) {
		Flux
			.fromIterable(SampleData.btcTopPricesPerYear)
			.takeLast(2)
			.subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
	}
}