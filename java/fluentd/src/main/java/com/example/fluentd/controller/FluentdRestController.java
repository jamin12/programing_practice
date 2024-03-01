package com.example.fluentd.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.fluentd.logger.FluentLogger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
public class FluentdRestController {
	private final FluentLogger logger = FluentLogger.getLogger("zxcv", "localhost", 9880);

	@GetMapping
	public void sendLog(MyLog message) {
		try {
			// ObjectMapper 인스턴스 생성
			ObjectMapper mapper = new ObjectMapper();

			// MyLog 객체를 JSON 문자열로 변환
			String jsonMessage = mapper.writeValueAsString(message);
			System.out.println("JSON representation: " + jsonMessage);

			// JSON 문자열을 Map으로 변환
			Map<String, Object> mapMessage = mapper.readValue(jsonMessage, new TypeReference<>() {
			});
			System.out.println("Map representation: " + mapMessage);

			logger.log("asdf", mapMessage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("test")
	public void sendLog2(String name, int gae) {
		Map<String, Object> mapMessage = new HashMap<>();

		mapMessage.put("name", name);
		mapMessage.put("gae", gae);
		logger.log("asdf", mapMessage);
	}

	private record MyLog(
		String myName,
		int myAge
	) {
	}
}

