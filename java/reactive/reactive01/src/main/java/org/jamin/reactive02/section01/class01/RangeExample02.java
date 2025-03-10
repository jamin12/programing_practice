package org.jamin.reactive02.section01.class01;

import java.util.List;

import org.jamin.reactive02.common.SampleData;
import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * range()의 사용 예제
 *  - 명령형 방식의 for 문을 대체하는 예제
 */
public class RangeExample02 {
	public static void main(String[] args) {
		List<String> coinNames = SampleData.coinNames;

		Flux
			.range(0, coinNames.size())
			.subscribe(index -> Logger.onNext(coinNames.get(index)));
	}
}