package com.example.ssepractice.security.dto;

import java.util.UUID;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class JwtRequest {
	@NotNull
	@Schema(description = "엑세스 토큰")
	private UUID accessTokenId;
	@NotNull
	@Schema(description = "리프레쉬 토큰")
	private UUID refreshTokenId;

	public JwtRequest(UUID accessTokenId, UUID refreshTokenId) {
		this.accessTokenId = accessTokenId;
		this.refreshTokenId = refreshTokenId;
	}
}
