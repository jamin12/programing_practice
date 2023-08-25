package com.example.fcmpractice.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.fcmpractice.security.application.AuthService;
import com.example.fcmpractice.security.dto.JwtRequest;
import com.example.fcmpractice.security.dto.JwtResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "인증 관리")
public class AuthController {
	private final AuthService authService;

	@PostMapping("/regenerate")
	public ResponseEntity<JwtResponse> getRegenerateToken(@RequestBody JwtRequest jwtRequest) {
		return ResponseEntity.ok().body(authService.regenerateToken(jwtRequest));
	}
}
