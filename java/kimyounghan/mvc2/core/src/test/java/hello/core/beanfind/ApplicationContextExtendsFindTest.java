package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    void 부모_타입으로_조회시_자식이_둘_이상있으면_오류가_발생한다() {
        assertThatThrownBy(() -> ac.getBean(DiscountPolicy.class))
                .isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    void 부모_타입으로_조회시_자식이_둘_이상있으면_이름을_지정하면_된다() {
        DiscountPolicy bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

        assertThat(bean).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    void 부모_타입으로_모두_조회하기() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);

        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    void 부모_타입으로_모두_조회하기_object() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
    }

    @Test
    void 특정_하위타입으로_조회() {
        DiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);

        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPlPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
