package com.example.jdbc.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.jdbc.domain.Member;
import com.example.jdbc.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 트랜잭션 - @Transactional aop
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV4_1 {
	private final MemberRepository memberRepository;

	@Transactional
	public void accountTransfer(String fromId, String toId, int money) {
		Member fromMember = memberRepository.findById(fromId);
		Member toMember = memberRepository.findById(toId);
		memberRepository.update(fromId, fromMember.getMoney() - money);
		validation(toMember);
		memberRepository.update(toId, toMember.getMoney() + money);
	}

	private void validation(Member toMember) {
		if (toMember.getMemberId().equals("ex")) {
			throw new IllegalStateException("이체중 예외 발생");
		}
	}
}