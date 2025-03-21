package org.jamin.reactive02.common;

import java.util.Map;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

public class CoronaVaccineService {
	private static Map<SampleData.CoronaVaccine, Tuple2<SampleData.CoronaVaccine, Integer>> vaccineMap = getCoronaVaccinesMap();

	public static Mono<Boolean> isGreaterThan(SampleData.CoronaVaccine coronaVaccine, int amount) {
		return Mono
			.just(vaccineMap.get(coronaVaccine).getT2() > amount)
			.publishOn(Schedulers.parallel());
	}

	private static Map<SampleData.CoronaVaccine, Tuple2<SampleData.CoronaVaccine, Integer>> getCoronaVaccinesMap() {
		return SampleData.getCoronaVaccinesMap();
	}
}