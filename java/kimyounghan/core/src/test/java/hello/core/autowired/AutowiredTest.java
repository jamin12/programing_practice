package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void autowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    @Configuration
    static class TestBean {
        @Autowired(required = false)
        public void setNoBean1(Member nobean1) {
            System.out.println("TestBean.setNoBean1" + nobean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member nobean1) {
            System.out.println("TestBean.setNoBean2" + nobean1);
        }

        @Autowired
        public void setNoBean3(Optional<Member> nobean1) {
            System.out.println("TestBean.setNoBean3" + nobean1);
        }
    }
}
