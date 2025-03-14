package org.jamin.reactive02.section03.class07.class08;

import reactor.core.publisher.Flux;

import org.jamin.reactive02.common.SampleData;
import org.jamin.reactive02.utils.Logger;

/**
 * collectList 활용 예제
 * - emit된 세 개의 모스 부호를 List<String> 타입으로 Downstream에 emit한다.
 */
public class CollectListExample01 {
	public static void main(String[] args) {
		Flux
			.just("...", "---", "...")
			.map(CollectListExample01::transformMorseCode)
			.collectList()
			.subscribe(list -> Logger.onNext(String.join("", list)));
	}

	public static String transformMorseCode(String morseCode) {
		return SampleData.morseCodeMap.get(morseCode);
	}
}
