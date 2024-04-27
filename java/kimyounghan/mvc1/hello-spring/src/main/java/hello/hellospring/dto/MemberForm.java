package hello.hellospring.dto;

import hello.hellospring.domain.Member;

public class MemberForm {
	private String name;

	public MemberForm(String name) {
		this.name = name;
	}

	public Member toMember(){
		return new Member(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
