package hello.aop.exam;

import org.springframework.stereotype.Repository;

import hello.aop.exam.annotation.Retry;
import hello.aop.exam.annotation.Trace;

@Repository
public class ExamRepository {
	private static int seq = 0;

	@Trace
	@Retry(4)
	public String save(String itemId) {
		seq++;
		if (seq % 5 == 0) {
			throw new IllegalStateException("5번째 save() 호출");
		}
		return "ok";
	}
}
