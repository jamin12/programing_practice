package org.jamin.reactive02.section02.class05;

import java.time.Duration;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;
import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 파라미터로 입력된 Publisher가 onNext 또는 onComplete signal을 발생시킬 때까지 Upstream에서 emit된 데이터만
 * Downstream에 emit 한다.
 */
public class TakeUntilOtherExample {
	public static void main(String[] args) {
		Flux.interval(Duration.ofMillis(300))
			.takeUntilOther(doSomeTask())
			.subscribe(Logger::onNext);

		TimeUtils.sleep(2000);
	}

	private static Publisher<?> doSomeTask() {
		return Mono.delay(Duration.ofSeconds(1));
	}
}