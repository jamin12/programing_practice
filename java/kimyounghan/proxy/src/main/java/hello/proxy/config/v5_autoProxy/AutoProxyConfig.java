package hello.proxy.config.v5_autoProxy;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v3_proxfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {
	// @Bean
	public Advisor getAdvisor1(LogTrace logTrace) {
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedNames("request*", "order*", "save*");
		LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);

		return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
	}

	// @Bean
	public Advisor getAdvisor2(LogTrace logTrace) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* hello.proxy.app..*(..))");
		LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);

		return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
	}

	@Bean
	public Advisor getAdvisor3(LogTrace logTrace) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))");
		LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);

		return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
	}
}
