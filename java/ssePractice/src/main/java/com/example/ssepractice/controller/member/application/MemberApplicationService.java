package com.example.ssepractice.controller.member.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ssepractice.controller.member.dto.SignupRequest;
import com.example.ssepractice.domain.member.domain.Role;
import com.example.ssepractice.domain.member.service.MemberService;
import com.example.ssepractice.security.application.AuthService;
import com.example.ssepractice.security.dto.JwtRequest;
import com.example.ssepractice.security.dto.JwtResponse;
import com.example.ssepractice.security.dto.MemberSecurityRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberApplicationService {
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private final MemberService memberService;
	private final AuthService authService;

	public JwtResponse login(MemberSecurityRequest memberSecurityRequest) {
		return authService.generateToken(memberSecurityRequest);
	}

	public void logout(JwtRequest jwtRequest) {
		authService.deleteToken(jwtRequest);
	}

	public void signup(MemberSecurityRequest memberSecurityRequest, Role role) {
		SignupRequest signupRequest = new SignupRequest(
			memberSecurityRequest.getMemberId(),
			passwordEncoder.encode(memberSecurityRequest.getPassword()),
			role
		);
		memberService.signup(signupRequest.toMember());
	}
}
