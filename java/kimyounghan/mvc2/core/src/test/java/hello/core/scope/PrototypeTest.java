package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PrototypeTest {
    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);

        System.out.println("find bean1");
        ProtoTypeBean bean1 = ac.getBean(ProtoTypeBean.class);
        System.out.println("find bean2");
        ProtoTypeBean bean2 = ac.getBean(ProtoTypeBean.class);

        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);

        assertThat(bean1).isNotSameAs(bean2);

        ac.close();
    }


    @Scope("prototype")
    static class ProtoTypeBean {
        @PostConstruct
        public void init() {
            System.out.println("ProtoTypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("ProtoTypeBean.destroy");
        }
    }
}
