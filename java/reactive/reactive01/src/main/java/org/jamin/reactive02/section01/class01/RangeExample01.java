package org.jamin.reactive02.section01.class01;

import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * range()의 사용 예제
 *  - 첫번째 파라미터(start)부터 두번째 파라미터(count)만큼 1씩 증가한 연속된 정수를 emit한다.
 */
public class RangeExample01 {
	public static void main(String[] args) {
		Flux
			.range(5, 10)
			.subscribe(Logger::onNext);
	}
}