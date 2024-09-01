package hello.advanced.trace.strategy;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.ContextV2;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ContextV2Test {

	@Test
	void strategyV2() {
		ContextV2 context1 = new ContextV2();
		context1.execute(() -> log.info("비즈니스 로직 1 실행"));

		context1.execute(() -> log.info("비즈니스 로직 2 실행"));
	}

}
