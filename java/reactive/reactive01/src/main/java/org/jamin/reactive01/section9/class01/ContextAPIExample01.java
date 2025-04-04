package org.jamin.reactive01.section9.class01;

import org.jamin.reactive01.utils.Logger;
import org.jamin.reactive01.utils.TimeUtils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

/**
 * Context API 중에서 write API 예제 코드
 * - Context.of(...) 사용
 */
public class ContextAPIExample01 {
	public static void main(String[] args) {
		String key1 = "id";
		String key2 = "name";
		Mono<String> mono =
			Mono.deferContextual(ctx ->
					Mono.just("ID: " + " " + ctx.get(key1) + ", " + "Name: " + ctx.get(key2))
				)
				.publishOn(Schedulers.parallel())
				.contextWrite(Context.of(key1, "itVillage", key2, "Kevin"));

		mono.subscribe(Logger::onNext);

		TimeUtils.sleep(100L);
	}
}