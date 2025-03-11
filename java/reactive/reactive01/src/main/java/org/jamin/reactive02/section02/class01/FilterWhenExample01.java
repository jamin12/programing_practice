package org.jamin.reactive02.section02.class01;

import static org.jamin.reactive02.common.CoronaVaccineService.*;

import org.jamin.reactive02.common.SampleData;
import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;

import reactor.core.publisher.Flux;

/**
 * 백신의 재고를 기준 이상으로 보유하고 있는 백신만 출력하도록 하는 예제
 *  - filterWhen을 사용해서 비동기적으로 필터링을 한다.
 */
public class FilterWhenExample01 {
	public static void main(String[] args) {
		Flux
			.fromIterable(SampleData.coronaVaccineNames)
			.filterWhen(vaccine -> isGreaterThan(vaccine, 3_000_000))
			.subscribe(Logger::onNext);

		TimeUtils.sleep(1000);
	}
}