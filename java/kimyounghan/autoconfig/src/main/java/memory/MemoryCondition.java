package memory;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemoryCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String memory = context.getEnvironment().getProperty("memory");
		log.info("memory {}", memory);
		return "on".equals(memory);
	}
}
