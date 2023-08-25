package com.example.fcmpractice.controller.member.application;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fcmpractice.controller.member.dto.SignupRequest;
import com.example.fcmpractice.domain.member.domain.Member;
import com.example.fcmpractice.domain.member.domain.Role;
import com.example.fcmpractice.domain.member.service.MemberService;
import com.example.fcmpractice.security.application.AuthService;
import com.example.fcmpractice.security.dto.JwtRequest;
import com.example.fcmpractice.security.dto.JwtResponse;
import com.example.fcmpractice.security.dto.MemberSecurityRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberApplicationService {
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private final MemberService memberService;
	private final AuthService authService;

	public JwtResponse login(MemberSecurityRequest memberSecurityRequest) throws IOException {
		JwtResponse jwtResponse = authService.generateToken(memberSecurityRequest);
		Member byMemberId = memberService.getByMemberId(memberSecurityRequest.getMemberId());
		return jwtResponse;
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
