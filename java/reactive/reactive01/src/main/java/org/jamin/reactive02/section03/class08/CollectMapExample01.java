package org.jamin.reactive02.section03.class08;

import org.jamin.reactive02.common.SampleData;
import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * collectMap 활용 예제
 *  - 모스 부호를 key로, 모스 부호에 해당되는 알파벳을 value로 하는 Map을 반환하는 예제
 */
public class CollectMapExample01 {
	public static void main(String[] args) {
		Flux
			.range(0, 26)
			.collectMap(key -> SampleData.morseCodes[key], CollectMapExample01::transformToLetter)
			.subscribe(Logger::onNext);
	}

	private static String transformToLetter(int value) {
		return Character.toString((char)('a' + value));
	}
}
