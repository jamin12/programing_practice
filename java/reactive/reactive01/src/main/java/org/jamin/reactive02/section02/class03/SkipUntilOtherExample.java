package org.jamin.reactive02.section02.class03;

import java.time.Duration;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;
import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 파라미터로 입력된 Publisher가 onNext 또는 onComplete signal을 발생시킬 때까지 Upstream에서 emit된 데이터를 건너뛴다.
 */
public class SkipUntilOtherExample {
	public static void main(String[] args) {
		Flux.interval(Duration.ofSeconds(1))
			.skipUntilOther(doSomeTask())
			.subscribe(Logger::onNext);

		TimeUtils.sleep(4000);
	}

	private static Publisher<?> doSomeTask() {
		return Mono.delay(Duration.ofMillis(2500));
	}
}