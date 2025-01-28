package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import hello.order.v0.OrderConfigV0;
import hello.order.v1.OrderConfigV1;
import hello.order.v3.OrderConfigV3;

@SpringBootApplication(scanBasePackages = "hello.controller")
@Import(OrderConfigV3.class)
public class ActuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActuatorApplication.class, args);
	}

	@Bean
	public InMemoryHttpExchangeRepository inMemoryHttpExchangeRepository() {
		return new InMemoryHttpExchangeRepository();
	}

}
