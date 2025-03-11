package org.jamin.reactive02.section02.class01;

import org.jamin.reactive02.common.SampleData;
import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * 비트코인의 연간 최고가격이 1000만원을 넘는 연도와 가격을 필터링하는 예제
 */
public class FilterExample02 {
	public static void main(String[] args) {
		Flux
			.fromIterable(SampleData.btcTopPricesPerYear)
			.filter(tuple -> tuple.getT2() > 10_000_000)
			.subscribe(filtered -> Logger.onNext(filtered.getT1() + ":" + filtered.getT2()));
	}
}