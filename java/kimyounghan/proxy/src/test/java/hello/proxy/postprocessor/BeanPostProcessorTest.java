package hello.proxy.postprocessor;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

public class BeanPostProcessorTest {
	@Test
	void basicConfig() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

		// beanA 이름으로 B 객체가 빈으로 등록됨
		B b = applicationContext.getBean("beanA", B.class);
		b.helloA();


		assertThatThrownBy(() -> applicationContext.getBean("beanB", B.class))
			.isInstanceOf(NoSuchBeanDefinitionException.class);
	}

	@Slf4j
	@Configuration
	static class BeanPostProcessorConfig {
		@Bean(name = "beanA")
		public A a() {
			return new A();
		}

		@Bean
		public AToBPostProcessor helloPostProcessor() {
			return new AToBPostProcessor();
		}
	}

	@Slf4j
	static class A {
		public void helloA() {
			log.info("hello A");
		}
	}

	@Slf4j
	static class B {
		public void helloA() {
			log.info("hello B");
		}
	}

	@Slf4j
	static class AToBPostProcessor implements BeanPostProcessor {
		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			log.info("beanName={}, bean={}", beanName, bean);
			if (bean instanceof A) {
				return new B();
			}
			return bean;
		}
	}
}
