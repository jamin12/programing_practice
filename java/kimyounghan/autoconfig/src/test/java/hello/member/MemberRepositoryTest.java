package hello.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {
	@Autowired
	MemberRepository memberRepository;

	@Transactional
	@Test
	void memberTest() {
		Member member = new Member("idA", "memberA");
		memberRepository.initTable();
		memberRepository.save(member);

		Member findMember = memberRepository.findById(member.getMemberId());
		assertEquals(member.getName(), findMember.getName());
		assertEquals(member.getMemberId(), findMember.getMemberId());
	}

}