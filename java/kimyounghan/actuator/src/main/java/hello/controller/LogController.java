package hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LogController {
	@GetMapping("/log")
	public String log() {
		log.trace("trace a message");
		log.debug("debug a message");
		log.info("info a message");
		log.warn("warn a message");
		log.error("error a message");
		return "Logged successfully";
	}
}
