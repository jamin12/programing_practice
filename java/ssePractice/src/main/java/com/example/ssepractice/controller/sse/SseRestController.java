package com.example.ssepractice.controller.sse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.ssepractice.service.SseService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "sse 테스트")
@RequestMapping(value = "/sse")
public class SseRestController {
	private final SseService sseService;

	@GetMapping(value = "/sub/{memberId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<SseEmitter> subSse(@PathVariable Long memberId) {
		return ResponseEntity.ok(sseService.subScribe(memberId));
	}
}
