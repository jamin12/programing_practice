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
class TxLevelTest {
	@Autowired
	private LevelService levelService;

	@Test
	void txTest() {
		levelService.write();
		levelService.read();
	}

	@TestConfiguration
	static class TxLevelTestConfig {
		@Bean
		public LevelService basicService() {
			return new LevelService();
		}
	}

	@Slf4j
	@Transactional(readOnly = true)
	static class LevelService {
		@Transactional
		public void write() {
			log.info("call write");
			printTxInfo();
		}

		public void read() {
			log.info("call notTx");
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
