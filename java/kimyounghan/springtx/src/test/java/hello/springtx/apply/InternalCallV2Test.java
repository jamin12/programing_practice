package hello.springtx.apply;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class InternalCallV2Test {
	@Autowired
	private CallService callService;
	@Autowired
	private InternalService internalService;

	@Test
	void proxyCheck() {
		log.info("apo class={}", callService.getClass());
		assertThat(AopUtils.isAopProxy(callService)).isTrue();
	}

	@Test
	void internalTest() {
		internalService.internal();
	}

	@Test
	void externalTest() {
		callService.external();
	}

	@TestConfiguration
	static class CallServiceTestConfig {
		@Bean
		public CallService callService() {
			return new CallService(internalService());
		}

		@Bean
		public InternalService internalService() {
			return new InternalService();
		}
	}

	@Slf4j
	@RequiredArgsConstructor
	static class CallService {
		private final InternalService service;

		public void external() {
			log.info("external call");
			printTxInfo();
			service.internal();
		}

		private void printTxInfo() {
			boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
			log.info("tx active={}", txActive);
			boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
			log.info("tx readOnly={}", readOnly);
		}
	}

	@Slf4j
	static class InternalService {

		@Transactional
		public void internal() {
			log.info("internal call");
			printTxInfo();
		}

		private void printTxInfo() {
			boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
			log.info("tx active={}", txActive);
			boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
			log.info("tx readOnly={}", readOnly);
		}
	}
}
