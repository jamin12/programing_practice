package hello.hellospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {
	// @Bean
	// public MemberService memberService(){
	// 	return new MemberService(memberRepository());
	// }
	//
	// @Bean
	// public MemberRepository memberRepository(){
	// 	return new MemoryMemberRepository();
	// }
}
