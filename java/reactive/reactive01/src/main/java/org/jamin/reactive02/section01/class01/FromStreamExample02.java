package org.jamin.reactive02.section01.class01;

import org.jamin.reactive02.common.SampleData;
import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/***
 * fromStream()의 사용 예제
 *  - Stream을 return 하는 supplier를 파라미터로 입력 받아 return되는 Stream에 포함된 데이터를 차례대로 emit 한다.
 */
public class FromStreamExample02 {
	public static void main(String[] args) {
		Flux
			.fromStream(SampleData.coinNames::stream)
			.filter(coin -> coin.equals("BTC") || coin.equals("ETH"))
			.subscribe(Logger::onNext);
	}
}