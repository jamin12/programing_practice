package org.jamin.reactive02.section08.class00;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;

/**
 * publish() 개념 예제
 *  - 다수의 Subscriber와 Flux를 공유한다.
 *  - Cold Sequence를 Hot Sequence로 변환한다.
 *  - connect()가 호출 되기 전 까지는 데이터를 emit하지 않는다.
 */
public class PublishExample01 {
	public static void main(String[] args) {
		ConnectableFlux<Integer> flux =
			Flux.range(1, 5)
				.delayElements(Duration.ofMillis(300L))
				.publish();

		TimeUtils.sleep(500L);
		flux.subscribe(data -> Logger.onNext("subscriber1: ", data));

		TimeUtils.sleep(200L);
		flux.subscribe(data -> Logger.onNext("subscriber2: ", data));

		flux.connect();

		TimeUtils.sleep(1000L);
		flux.subscribe(data -> Logger.onNext("subscriber3: ", data));

		TimeUtils.sleep(2000L);
	}
}