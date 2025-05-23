package org.jamin.reactive02.section01.class01;

import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Mono;

/**
 * just()에 null 값을 입력하면 NullPointException 이 발생하는 예제
 */
public class JustOrEmptyExample01 {
	public static void main(String[] args) {
		Mono.just(null)
			.log()
			.subscribe(Logger::onNext);
	}
}
