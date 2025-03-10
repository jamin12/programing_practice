package org.jamin.reactive02.section01.class04;

import java.util.Map;

import org.jamin.reactive02.common.SampleData;
import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

/**
 * generate() 만으로 데이터를 필터링 하는 예제
 *  - 2016년도 이후의 해당 연도(2017년 - 2021년)의 BTC 최고가 금액을 출력하는 예제
 */
public class GenerateExample03 {
	public static void main(String[] args) {
		Map<Integer, Tuple2<Integer, Long>> map = SampleData.getBtcTopPricesPerYearMap();
		Flux
			.generate(() -> 2017, (state, sink) -> {
				if (state > 2021) {
					sink.complete();
				} else {
					sink.next(map.get(state));
				}

				return ++state;
			})
			.subscribe(Logger::onNext);
	}
}