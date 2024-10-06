package hello.aop.internalcall;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InternalService {
	public void internalCall() {
		log.info("internal call");
	}
}
