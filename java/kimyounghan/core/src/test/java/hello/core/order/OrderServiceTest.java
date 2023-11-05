package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();
    private MemberService memberService = new MemberServiceImpl();
    @Test
    void createOrder(){
        // given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // when
        Order itemA = orderService.createOrder(memberId, "itemA", 10000);

        // then
        assertThat(itemA.getDiscountPrice()).isEqualTo(1000);

    }
}
