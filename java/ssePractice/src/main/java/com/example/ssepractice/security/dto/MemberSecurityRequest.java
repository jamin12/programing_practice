package com.example.ssepractice.security.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class MemberSecurityRequest {
	@NotBlank
	@Schema(description = "유저 아이디")
	private String memberId;
	@NotBlank
	@Schema(description = "유저 비밀번호")
	private String password;

	public MemberSecurityRequest(String memberId, String password) {
		this.memberId = memberId;
		this.password = password;
	}
}
