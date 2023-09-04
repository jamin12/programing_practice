package com.example.emailparsing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emailparsing.service.EmailParsingService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@Tag(name = "메일 파싱")
@RequiredArgsConstructor
public class EmailParsingRestController {
	private final EmailParsingService emailParsingService;

	@GetMapping
	public void get() {
		emailParsingService.getEmail();
	}
}
