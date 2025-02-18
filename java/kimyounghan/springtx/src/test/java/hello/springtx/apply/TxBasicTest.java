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
class TxBasicTest {
	@Autowired
	private BasicService basicService;

	@Test
	void proxyCheck() {
		log.info("apo class={}", basicService.getClass());
		assertThat(AopUtils.isAopProxy(basicService)).isTrue();
	}

	@Test
	void txTest() {
		basicService.tx();
		basicService.notTx();
	}

	@TestConfiguration
	static class TxApplyBasicConfig {
		@Bean
		public BasicService basicService() {
			return new BasicService();
		}
	}

	@Slf4j
	static class BasicService {
		@Transactional
		public void tx() {
			log.info("call tx");
			boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
			log.info("tx active = {}", txActive);
		}

		public void notTx() {
			log.info("call notTx");
			boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
			log.info("tx active = {}", txActive);
		}
	}
}
