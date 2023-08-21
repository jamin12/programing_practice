package com.example.ssepractice.security.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class JwtResponse {
	@Schema(description = "엑세스 토큰 아이디")
	private UUID accessTokenId;
	@Schema(description = "엑세스 토큰")
	private String accessToken;
	@Schema(description = "리프레쉬 토큰 아이디")
	private UUID refreshTokenId;
	@Schema(description = "엑세스 토큰")
	private String refreshToken;

	public JwtResponse(UUID accessTokenId, String accessToken, UUID refreshTokenId, String refreshToken) {
		this.accessTokenId = accessTokenId;
		this.accessToken = accessToken;
		this.refreshTokenId = refreshTokenId;
		this.refreshToken = refreshToken;
	}
}
