package org.jamin.reactive02.section02.class03;

import org.jamin.reactive02.common.SampleData;
import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * skipLast 기본 예제
 *  - emit 된 데이터 중에서 파라미터로 입력된 갯수만큼 가장 마지막에 emit 된 데이터부터 건너뛴다.
 */
public class SkipLastExample {
	public static void main(String[] args) {
		Flux
			.fromIterable(SampleData.btcTopPricesPerYear)
			.skipLast(2)
			.subscribe(tuple -> Logger.onNext(tuple.getT1(), tuple.getT2()));
	}
}