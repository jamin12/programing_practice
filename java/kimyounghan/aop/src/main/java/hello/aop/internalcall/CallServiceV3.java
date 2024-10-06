package hello.aop.internalcall;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CallServiceV3 {
	private final InternalService service;

	public CallServiceV3(InternalService service) {
		this.service = service;
	}

	public void external() {
		log.info("call external");
		service.internalCall();
	}
}
