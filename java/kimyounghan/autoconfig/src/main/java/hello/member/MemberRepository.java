package hello.member;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	public final JdbcTemplate jdbcTemplate;

	public void initTable() {
		jdbcTemplate.execute("create table member(member_id varchar(255) not null primary key , name varchar(255))");
	}

	public void save(Member member) {
		jdbcTemplate.update("insert into member(member_id,name) values (?,?)", member.getMemberId(), member.getName());
	}

	public Member findById(String memberId) {
		return jdbcTemplate.queryForObject("select * from member where member_id=?",
			BeanPropertyRowMapper.newInstance(Member.class), memberId);
	}

	public List<Member> findAll() {
		return jdbcTemplate.query("select member_id, name from member",
			BeanPropertyRowMapper.newInstance(Member.class));
	}
}
