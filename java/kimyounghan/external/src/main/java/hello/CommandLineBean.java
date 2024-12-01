package hello;

import java.util.List;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CommandLineBean {
	private final ApplicationArguments arguments;

	@PostConstruct
	public void init() {
		log.info("source {}", List.of(arguments.getSourceArgs()));
		log.info("optionNames {}", arguments.getOptionNames());
		Set<String> optionNames = arguments.getOptionNames();
		for (String optionName : optionNames) {
			log.info("option args {}={}", optionName,
				arguments.getOptionValues(optionName));
		}
	}
}
