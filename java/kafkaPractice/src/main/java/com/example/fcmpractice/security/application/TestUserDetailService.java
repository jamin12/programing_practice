package com.example.fcmpractice.security.application;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.fcmpractice.controller.member.dto.MemberAdepter;
import com.example.fcmpractice.domain.member.domain.Member;
import com.example.fcmpractice.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestUserDetailService implements UserDetailsService {
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String memberId) {
		Member member = memberRepository.findByMemberId(memberId)
			.orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));

		return new MemberAdepter(member);
	}
}
