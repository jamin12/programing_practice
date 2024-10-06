package hello.aop.internalcall;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CallServiceV2 {
	// private final ApplicationContext applicationContext;
	private final ObjectProvider<CallServiceV2> serviceV2ObjectProvider;

	public CallServiceV2(ObjectProvider<CallServiceV2> serviceV2ObjectProvider) {
		this.serviceV2ObjectProvider = serviceV2ObjectProvider;
	}

	public void external() {
		log.info("call external");
		CallServiceV2 bean = serviceV2ObjectProvider.getObject(CallServiceV2.class);
		bean.internalCall();
	}

	public void internalCall() {
		log.info("internal call");
	}
}
