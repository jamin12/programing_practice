package com.example.fcmpractice.controller.kafka.ui;

import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("kafka")
@RequiredArgsConstructor
@Slf4j
public class ProducerController {

	private final KafkaTemplate<String, String> kafkaTemplate;

	@GetMapping("/publish/mytopic1")
	public String publishSpringTopic1(@RequestParam String hihi) {
		String message = "publish message to my_topic_1 " + UUID.randomUUID();

		kafkaTemplate.send("testTopic", hihi);
		return "done";
	}
}