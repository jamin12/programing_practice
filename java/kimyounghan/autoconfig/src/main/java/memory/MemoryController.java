package memory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemoryController {
	private final MemoryFinder memoryFinder;

	@GetMapping
	public Memory system() {
		Memory memory = memoryFinder.get();
		log.info("Memory: {}", memory);
		return memory;
	}
}
