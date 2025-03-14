package org.jamin.reactive02.section05.class00;

import reactor.core.publisher.Flux;

import java.time.Duration;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;

/**
 * delaySubscription 기본 개념 예제
 *  - 파라미터로 입력한 시간만큼 구독을 지연 시킨다.
 */
public class DelaySubscriptionExample01 {
    public static void main(String[] args) {
        Flux
            .range(1, 10)
            .doOnSubscribe(subscription -> Logger.info("# doOnSubscribe > upstream"))
            .delaySubscription(Duration.ofSeconds(2))
            .doOnSubscribe(subscription -> Logger.info("# doOnSubscribe > downstream"))
            .subscribe(Logger::onNext);

        TimeUtils.sleep(2500);
    }
}
