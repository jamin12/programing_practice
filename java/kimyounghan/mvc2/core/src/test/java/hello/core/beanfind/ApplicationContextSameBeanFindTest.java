package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    void 타입으로_조회시_같은_타입이_둘_이상_있으면_오류가_발생한다() {
        assertThatThrownBy(() -> ac.getBean(MemberRepository.class))
                .isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    void 타입으로_조회시_같은_타입이_둘_이상_있으면_빈_이름을_지정하면_된다() {
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);

        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    }

    @Test
    void 특정_타입을_모두_조회하기() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
