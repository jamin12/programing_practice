package hello.core.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemberServiceTest {
    private MemberService memberService = new MemberServiceImpl();
    @Test
    void join(){
        // given
        Member memberA = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(memberA);
        Member member = memberService.findMember(1L);

        // then
        assertThat(member).isEqualTo(memberA);

    }
}
