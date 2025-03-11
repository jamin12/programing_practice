package org.jamin.reactive02.section02.class01;

import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * Filter 기본 개념 예제
 */
public class FilterExample01 {
	public static void main(String[] args) {
		Flux
			.range(1, 20)
			.filter(num -> num % 2 == 0)
			.subscribe(Logger::onNext);
	}
}