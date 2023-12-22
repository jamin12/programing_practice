package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SingletonTest {
    @Test
    void 스프링_없는_순수한_DI_컨테이너() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();

        MemberService memberService1 = appConfig.memberService();

        assertThat(memberService1).isNotSameAs(memberService);
    }

    @Test
    void 싱글톤_패턴을_적용햔_객체_사용() {
        SingletonService instance = SingletonService.getInstance();
        SingletonService instance1 = SingletonService.getInstance();

        assertThat(instance).isSameAs(instance1);

    }

    @Test
    void 스프링_컨테이너와_싱글톤() {
        AnnotationConfigApplicationContext appConfig = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = appConfig.getBean("memberService", MemberService.class);

        MemberService memberService1 = appConfig.getBean("memberService", MemberService.class);

        assertThat(memberService1).isSameAs(memberService);
    }
}
