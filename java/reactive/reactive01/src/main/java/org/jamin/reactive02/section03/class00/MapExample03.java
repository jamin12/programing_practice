package org.jamin.reactive02.section03.class00;

import org.jamin.reactive02.common.SampleData;
import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * map 활용 예제
 *  - Morse Code를 알파벳으로 변환하는 예제
 */
public class MapExample03 {
	public static void main(String[] args) {
		Flux
			.just("...", "---", "...")
			.map(MapExample03::transformMorseCode)
			.subscribe(Logger::onNext);
	}

	public static String transformMorseCode(String morseCode) {
		return SampleData.morseCodeMap.get(morseCode);
	}
}
