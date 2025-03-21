package org.jamin.reactive02.section03.class00;

import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * map 기본 개념 예제
 *  - Upstream에서 emit 된 데이터를 변환한 후, Downstream으로 emit한다.
 */
public class MapExample01 {
	public static void main(String[] args) {
		Flux
			.just("Green-Circle", "Yellow-Circle", "Blue-Circle")
			.map(circle -> circle.replace("Circle", "Rectangle"))
			.subscribe(Logger::onNext);

	}
}

