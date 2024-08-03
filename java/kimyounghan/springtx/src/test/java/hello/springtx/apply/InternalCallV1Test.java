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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class InternalCallV1Test {
	@Autowired
	private CallService callService;

	@Test
	void proxyCheck() {
		log.info("apo class={}", callService.getClass());
		assertThat(AopUtils.isAopProxy(callService)).isTrue();
	}

	@Test
	void internalTest() {
		callService.internal();
	}

	@Test
	void internalTest2() {
		callService.internal2();
	}

	@Test
	void internalTest3() {
		callService.internal3();
	}

	@Test
	void externalTest() {
		callService.external();
	}

	@TestConfiguration
	static class CallServiceTestConfig {
		@Bean
		public CallService callService() {
			return new CallService();
		}
	}

	@Slf4j
	static class CallService {
		public void external() {
			log.info("external call");
			printTxInfo();
			internal();
		}

		@Transactional
		public void internal() {
			log.info("internal call");
			printTxInfo();
		}

		public void external2() {
			log.info("internal call");
			printTxInfo();
		}

		@Transactional
		public void internal2() {
			log.info("internal call");
			printTxInfo();
			external2();
		}

		@Transactional(readOnly = true)
		public void external3() {
			log.info("internal call");
			printTxInfo();
		}

		@Transactional
		public void internal3() {
			log.info("internal call");
			printTxInfo();
			external3();
		}

		private void printTxInfo() {
			boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
			log.info("tx active={}", txActive);
			boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
			log.info("tx readOnly={}", readOnly);
		}
	}
}
