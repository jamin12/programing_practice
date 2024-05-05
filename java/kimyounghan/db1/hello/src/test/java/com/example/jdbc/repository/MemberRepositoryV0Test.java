package com.example.jdbc.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import com.example.jdbc.domain.Member;

class MemberRepositoryV0Test {
	MemberRepositoryV0 repositoryV0 = new MemberRepositoryV0();

	@Test
	void crud() throws SQLException {
		// save
		Member member = new Member("memberV2", 10000);

		repositoryV0.save(member);

		// findById
		Member findMember = repositoryV0.findById(member.getMemberId());
		assertThat(findMember).isEqualTo(member);

		// update
		repositoryV0.update(member.getMemberId(), 20000);
		Member updateMember = repositoryV0.findById(member.getMemberId());
		assertThat(updateMember.getMoney()).isEqualTo(20000);

		// delete
		repositoryV0.delete(member.getMemberId());
		assertThatThrownBy(() -> repositoryV0.findById(member.getMemberId()))
			.isInstanceOf(NoSuchElementException.class);
	}
}