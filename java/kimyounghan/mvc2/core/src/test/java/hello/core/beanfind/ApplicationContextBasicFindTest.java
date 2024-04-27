package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    void 빈_이름으로_조회() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    void 이름_없이_타입으로만_조회() {
        MemberService memberService = ac.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    void 구체_타입으로_조회() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    void 빈_이름으로_조회_안됨() {
//        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThatThrownBy(() -> ac.getBean("xxxx", MemberService.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}
