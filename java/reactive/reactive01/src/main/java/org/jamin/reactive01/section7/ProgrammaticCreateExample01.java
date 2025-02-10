package org.jamin.reactive01.section7;

import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

/**
 * create() Operator를 사용하는 예제
 *  - 일반적으로 Publisher의 데이터 생성을 단일 쓰레드에서 진행한다. 멀티 쓰레드에서도 가능
 *  - 데이터 emit은 create Operator 내부에서 가능.
 *  - Backpressure 적용 가능
 */
@Slf4j
public class ProgrammaticCreateExample01 {
	public static void main(String[] args) throws InterruptedException {
		int tasks = 6;
		Flux.create((FluxSink<String> sink) -> {
				IntStream.range(1, tasks)
					.forEach(n -> sink.next(doTask(n)));
			})
			.subscribe(data -> log.info("# onNext: {}", data));
		Thread.sleep(500L);
	}

	private static String doTask(int taskNumber) {
		return "task " + taskNumber + " result";
	}
}
