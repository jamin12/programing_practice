package hello.aop.exam;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import hello.aop.exam.aop.RetryAspect;
import hello.aop.exam.aop.TraceAspect;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Import({TraceAspect.class, RetryAspect.class})
@SpringBootTest
public class ExamTest {
	@Autowired
	ExamService examService;

	@Test
	void test() {
		IntStream.range(0, 5).forEach(i -> {
			log.info("Exam i = {}", i);
			examService.request("data" + i);
		});
	}
}
