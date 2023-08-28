package com.example.fcmpractice.controller.kafka.ui;

import java.time.LocalDateTime;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("kafka")
@RequiredArgsConstructor
@Slf4j
public class ProducerController {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/publish/mytopic1")
	public String publishSpringTopic1(@RequestBody TestDto testDto) throws JsonProcessingException {
		String s = objectMapper.writeValueAsString(testDto);
		kafkaTemplate.send("testTopic", s);
		return "done";
	}

	@Getter
	@NoArgsConstructor
	public static class TestDto {
		private Long id;
		private String name;
		private LocalDateTime localDateTime;

		public TestDto(Long id, String name, LocalDateTime localDateTime) {
			this.id = id;
			this.name = name;
			this.localDateTime = localDateTime;
		}
	}
}