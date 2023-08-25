package com.example.fcmpractice.controller.member.dto;

import com.example.fcmpractice.domain.member.domain.Member;
import com.example.fcmpractice.domain.member.domain.Role;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class SignupRequest {
	@NotBlank
	private String memberId;
	@NotBlank
	private String password;
	private Role role = Role.ROLE_ADMIN;

	public SignupRequest(String memberId, String password) {
		this.memberId = memberId;
		this.password = password;
	}

	public SignupRequest(String memberId, String password, Role role) {
		this.memberId = memberId;
		this.password = password;
		this.role = role;
	}

	public Member toMember() {
		return new Member(null, memberId, password, role);
	}
}
