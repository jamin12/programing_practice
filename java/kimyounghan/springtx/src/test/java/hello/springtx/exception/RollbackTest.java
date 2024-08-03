package hello.springtx.exception;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
public class RollbackTest {
	@Autowired
	RollBackService service;

	@Test
	void runtimeException() {
		assertThatThrownBy(() -> service.runtimeException())
			.isInstanceOf(RuntimeException.class);
	}

	@Test
	void checkedException() {
		assertThatThrownBy(() -> service.checkedException())
			.isInstanceOf(MyException.class);
	}

	@Test
	void rollbackFor() {
		assertThatThrownBy(() -> service.rollbackFor())
			.isInstanceOf(MyException.class);
	}

	@TestConfiguration
	static class RollbackTestConfig {
		@Bean
		RollBackService rollbackService() {
			return new RollBackService();
		}
	}

	@Slf4j
	static class RollBackService {
		@Transactional
		public void runtimeException() {
			log.info("call runtimeException");
			throw new RuntimeException();
		}

		@Transactional
		public void checkedException() throws Exception {
			log.info("call checkedException");
			throw new MyException();
		}

		@Transactional(rollbackFor = MyException.class)
		public void rollbackFor() throws Exception {
			log.info("call checkedException");
			throw new MyException();
		}
	}

	static class MyException extends Exception {
	}
}
