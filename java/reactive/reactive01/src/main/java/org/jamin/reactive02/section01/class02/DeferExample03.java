package org.jamin.reactive02.section01.class02;

import java.time.Duration;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;

import reactor.core.publisher.Mono;

/**
 * Defer 사용 예제
 *  - switchIfEmpty()에 파라미터로 입력되는 Sequence는 업스트림에서 emit 되는 데이터가 없을 경우 다운스트림에 emit한다.
 *  - 불필요한 호출을 방지하기 위해 실제 필요한 시점에 데이터를 emit하도록 defer()를 사용한다.
 */
public class DeferExample03 {
	public static void main(String[] args) {
		Logger.info("# Start");
		Mono
			.just("Hello")
			.delayElement(Duration.ofSeconds(3))
			.switchIfEmpty(Mono.defer(DeferExample03::sayDefault))
			.subscribe(Logger::onNext);

		TimeUtils.sleep(3500);
	}

	private static Mono<String> sayDefault() {
		Logger.info("# Say Hi");
		return Mono.just("Hi");
	}
}