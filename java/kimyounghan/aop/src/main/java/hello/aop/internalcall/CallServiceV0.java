package hello.aop.internalcall;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CallServiceV0 {
	public void external() {
		log.info("call external");
		internalCall();
	}

	public void internalCall() {
		log.info("internal call");
	}
}
