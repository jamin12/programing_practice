package com.example.ssepractice.domain.member.service;

import org.springframework.stereotype.Service;

import com.example.ssepractice.domain.member.domain.Member;
import com.example.ssepractice.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public void signup(Member member) {
		memberRepository.save(member);
	}
}
