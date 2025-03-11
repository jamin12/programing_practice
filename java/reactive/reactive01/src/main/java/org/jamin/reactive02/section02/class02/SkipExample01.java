package org.jamin.reactive02.section02.class02;

import java.time.Duration;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;

import reactor.core.publisher.Flux;

/**
 * skip 기본 개념 예제
 *  - 파라미터로 입력한 숫자만큼 Upstream에서 emit 되는 데이터를 건너뛴 후, 건너뛴 다음(next) 데이터부터 Downstream으로 emit 한다.
 */
public class SkipExample01 {
	public static void main(String[] args) {
		Flux
			.interval(Duration.ofSeconds(1))
			.doOnNext(Logger::doOnNext)
			.skip(3)
			.subscribe(Logger::onNext);

		TimeUtils.sleep(5000L);
	}
}