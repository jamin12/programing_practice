package org.jamin.reactive02.section03.class03;

import java.util.List;

import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * concat 기본 개념 예제
 *  - 파라미터로 입력된 Iterable의 Publisher Sequence 들을 연결해서 차례대로 emit한다.
 */
public class ConcatExample02 {
	public static void main(String[] args) {
		List<Flux<Integer>> sources = List.of(Flux.just(1, 2, 3), Flux.just(4, 5, 6));

		Flux
			.concat(sources)
			.subscribe(Logger::onNext);
	}
}
