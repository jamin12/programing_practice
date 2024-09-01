package hello.advanced.trace.strategy;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class TemplateCallbackTest {

	@Test
	void strategyV2() {
		TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
		timeLogTemplate.execute(() -> log.info("비즈니스 로직 1 실행"));
		timeLogTemplate.execute(() -> log.info("비즈니스 로직 2 실행"));
	}
}
