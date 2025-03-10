package org.jamin.reactive02.section01.class01;

import java.util.Optional;

import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Mono;

/**
 * justOrEmpty() 의 사용 예제
 *  - justOrEmpty()에 Optional.isPresent() 가 true 가 아니라면, onNext emit 없이 onComplete 만 emit 한다.
 */
public class JustOrEmptyExample03 {
	public static void main(String[] args) {
		Mono
			.justOrEmpty(Optional.ofNullable(null))
			.log()
			.subscribe(Logger::onNext);
	}
}