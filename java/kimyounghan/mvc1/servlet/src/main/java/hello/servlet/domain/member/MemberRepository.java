package hello.servlet.domain.member;

import hello.servlet.basic.response.Member;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private static final Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용
    @Getter
    private static final MemberRepository instance = new MemberRepository();

    private MemberRepository() {
    }
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    public Member findById(Long id) {
        return store.get(id);
    }
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore() {
        store.clear();
    }
}