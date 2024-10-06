package hello.aop.proxyvs.code;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @SpringBootTest(properties = "spring.aop.proxy-target-class=false")
@SpringBootTest(properties = "spring.aop.proxy-target-class=true")
@Import(ProxyDiAspect.class)
public class ProxyDiTest {
	@Autowired
	MemberService memberService;
	@Autowired
	MemberServiceImpl memberServiceImpl;

	@Test
	void go() {
		log.info("member service = class = {}", memberService.getClass());
		log.info("member service impl = class = {}", memberServiceImpl.getClass());
		memberServiceImpl.hello("hello");
	}
}
