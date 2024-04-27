package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SingletonTest {
    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(singletonBean.class);

        singletonBean bean1 = ac.getBean(singletonBean.class);
        singletonBean bean2 = ac.getBean(singletonBean.class);

        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);

        assertThat(bean1).isSameAs(bean2);

        ac.close();
    }


    @Scope("singleton")
    static class singletonBean {
        @PostConstruct
        public void init() {
            System.out.println("singletonBea.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("singletonBea.destroy");
        }
    }
}
