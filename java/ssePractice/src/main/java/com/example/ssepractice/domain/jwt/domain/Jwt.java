package com.example.ssepractice.domain.jwt.domain;

import java.time.LocalDateTime;
import java.util.UUID;


import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Jwt {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	@Comment("토큰")
	@Enumerated(EnumType.STRING)
	private TokenType tokenType;
	@Comment("유효시간")
	private LocalDateTime expirationDate;
	@Comment("유저 아이디")
	private String memberId;

	public Jwt(UUID id, TokenType tokenType, LocalDateTime expirationDate) {
		this.id = id;
		this.tokenType = tokenType;
		this.expirationDate = expirationDate;
	}

	public void updateMemberId(String memberId) {
		this.memberId = memberId;
	}
}
