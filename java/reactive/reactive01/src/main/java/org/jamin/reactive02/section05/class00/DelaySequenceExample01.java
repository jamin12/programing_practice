package org.jamin.reactive02.section05.class00;

import reactor.core.publisher.Flux;

import java.time.Duration;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;

/**
 * delaySequence 기본 개념 예제
 *  - 구독은 즉시 이루어지지만 최초의 데이터 emit은 파라미터로 입력한 시간만큼 지연 시킨다.
 */
public class DelaySequenceExample01 {
    public static void main(String[] args) {
        Flux
            .range(1, 10)
            .doOnSubscribe(subscription -> Logger.doOnSubscribe())
            .delaySequence(Duration.ofSeconds(2))
            .doOnSubscribe(subscription -> Logger.doOnSubscribe())
            .subscribe(Logger::onNext);

        TimeUtils.sleep(2500);
    }
}
