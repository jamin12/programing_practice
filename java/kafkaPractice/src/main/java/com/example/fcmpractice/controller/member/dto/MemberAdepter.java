package com.example.fcmpractice.controller.member.dto;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.fcmpractice.domain.member.domain.Member;
import com.example.fcmpractice.domain.member.domain.Role;

import lombok.Getter;

@Getter
public class MemberAdepter extends User {
	private final Member member;

	public MemberAdepter(Member member) {
		super(member.getMemberId(), member.getPassword(),
			Collections.singleton(new SimpleGrantedAuthority(member.getRole().toString())));

		this.member = member;
	}

	public MemberAdepter(User user) {
		super(user.getUsername(), user.getPassword(), user.getAuthorities());
		GrantedAuthority grantedAuthority = user.getAuthorities()
			.stream()
			.findFirst()
			.orElseThrow(() -> new NoSuchElementException("No such user role"));

		this.member = new Member(null, user.getUsername(), user.getPassword(),
			Role.valueOf(grantedAuthority.getAuthority()));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof MemberAdepter that))
			return false;
		if (!super.equals(o))
			return false;
		return Objects.equals(getMember(), that.getMember());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getMember());
	}
}
