package com.example.jdbc.repository;

import static com.example.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.example.jdbc.domain.Member;
import com.zaxxer.hikari.HikariDataSource;

class MemberRepositoryV1Test {
	MemberRepositoryV1 repositoryV1;

	@BeforeEach
	void beforeEach() throws Exception {
		//기본 DriverManager - 항상 새로운 커넥션 획득
		//DriverManagerDataSource dataSource =
		// new DriverManagerDataSource(URL, USERNAME, PASSWORD);
		//커넥션 풀링: HikariProxyConnection -> JdbcConnection
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		repositoryV1 = new MemberRepositoryV1(dataSource);
	}

	@Test
	void crud() throws SQLException {
		// save
		Member member = new Member("memberV2", 10000);

		repositoryV1.save(member);

		// findById
		Member findMember = repositoryV1.findById(member.getMemberId());
		assertThat(findMember).isEqualTo(member);

		// update
		repositoryV1.update(member.getMemberId(), 20000);
		Member updateMember = repositoryV1.findById(member.getMemberId());
		assertThat(updateMember.getMoney()).isEqualTo(20000);

		// delete
		repositoryV1.delete(member.getMemberId());
		assertThatThrownBy(() -> repositoryV1.findById(member.getMemberId()))
			.isInstanceOf(NoSuchElementException.class);
	}
}