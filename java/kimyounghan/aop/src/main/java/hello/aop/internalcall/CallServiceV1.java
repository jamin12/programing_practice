package hello.aop.internalcall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CallServiceV1 {
	private CallServiceV1 service;

	@Autowired
	public void setCallServiceV1(CallServiceV1 service) {
		this.service = service;
	}

	public void external() {
		log.info("call external");
		service.internalCall();
	}

	public void internalCall() {
		log.info("internal call");
	}
}
