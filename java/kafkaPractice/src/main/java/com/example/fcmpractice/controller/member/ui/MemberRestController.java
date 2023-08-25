package com.example.fcmpractice.controller.member.ui;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fcmpractice.controller.member.application.MemberApplicationService;
import com.example.fcmpractice.domain.member.domain.Role;
import com.example.fcmpractice.security.dto.JwtRequest;
import com.example.fcmpractice.security.dto.JwtResponse;
import com.example.fcmpractice.security.dto.MemberSecurityRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "회원 관리")
public class MemberRestController {
	private final MemberApplicationService memberApplicationService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody MemberSecurityRequest memberSecurityRequest) throws
		IOException {
		return ResponseEntity.ok().body(memberApplicationService.login(memberSecurityRequest));
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(@Valid @RequestBody JwtRequest jwtRequest) {
		memberApplicationService.logout(jwtRequest);
		return ResponseEntity.ok().build();
	}

	// @GetMapping("/test")
	// public void test(@Parameter(hidden = true) @AuthenticationPrincipal UsersAdepter usersAdepter) {
	// 	System.out.println("======================================================================");
	// 	System.out.println(usersAdepter.getUsers().getUserId());
	// }

	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@Valid @RequestBody MemberSecurityRequest memberSecurityRequest,
		@RequestParam(required = false, defaultValue = "ROLE_ADMIN") Role role) {
		memberApplicationService.signup(memberSecurityRequest, role);
		return ResponseEntity.ok().build();
	}
}
