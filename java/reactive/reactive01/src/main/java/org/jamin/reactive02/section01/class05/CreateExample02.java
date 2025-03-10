package org.jamin.reactive02.section01.class05;

import java.util.List;

import org.jamin.reactive02.utils.Logger;
import org.jamin.reactive02.utils.TimeUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

/**
 * create 개념 이해 예제
 *  - Subscriber의 request와 상관없이 next signal 이벤트를 발생하는 예제
 */
public class CreateExample02 {
	public static void main(String[] args) {
		Logger.info("# start");

		CryptoCurrencyPriceEmitter priceEmitter = new CryptoCurrencyPriceEmitter();

		Flux.create((FluxSink<Integer> sink) -> {
				priceEmitter.setListener(new CryptoCurrencyPriceListener() {
					@Override
					public void onPrice(List<Integer> priceList) {
						priceList.forEach(sink::next);
					}

					@Override
					public void onComplete() {
						sink.complete();
					}
				});
			})
			.publishOn(Schedulers.parallel())
			.subscribe(
				Logger::onNext,
				error -> {
				},
				() -> Logger.info("# onComplete"));

		TimeUtils.sleep(3000L);

		priceEmitter.flowInto();

		TimeUtils.sleep(2000L);
		priceEmitter.complete();

		TimeUtils.sleep(100L);
	}
}