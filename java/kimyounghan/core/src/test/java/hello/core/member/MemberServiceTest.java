package hello.core.member;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemberServiceTest {
    private final AppConfig appConfig = new AppConfig();
    private final MemberService memberService = appConfig.memberService();
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
