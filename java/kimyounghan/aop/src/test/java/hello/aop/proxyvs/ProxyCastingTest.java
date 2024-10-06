package hello.aop.proxyvs;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyCastingTest {
	@Test
	void jdkProxy() {
		MemberServiceImpl target = new MemberServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setProxyTargetClass(false);
		// 프록시를 인터페이스로 캐스팅 성공
		MemberService proxy = (MemberService)proxyFactory.getProxy();

		// 프록시 구현클래스로 캐스팅 실패
		// MemberService proxyImpl = (MemberServiceImpl)proxyFactory.getProxy();
	}

	@Test
	void chLibProxy() {
		MemberServiceImpl target = new MemberServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setProxyTargetClass(true);
		// 프록시를 인터페이스로 캐스팅 성공
		MemberService proxy = (MemberService)proxyFactory.getProxy();

		// 프록시 구현클래스로 캐스팅 성공
		MemberService proxyImpl = (MemberServiceImpl)proxyFactory.getProxy();
	}

}
