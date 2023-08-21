package com.example.ssepractice.domain.member.domain;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	@Comment("아이디")
	private String memberId;
	@Comment("비밀번호")
	@Column(nullable = false)
	private String password;
	@Comment("권한")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	public Member(Long id, String memberId, String password, Role role) {
		this.id = id;
		this.memberId = memberId;
		this.password = password;
		this.role = role;
	}

	public Boolean isSuperAdmin() {
		return this.role.equals(Role.ROLE_SUPER_ADMIN);
	}
}
