package com.example.fcmpractice.domain.member.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.fcmpractice.domain.member.domain.Member;
import com.example.fcmpractice.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public void signup(Member member) {
		memberRepository.save(member);
	}

	public Member getByMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId)
			.orElseThrow(() -> new NoSuchElementException("유저갸 없습니다"));
	}
}
