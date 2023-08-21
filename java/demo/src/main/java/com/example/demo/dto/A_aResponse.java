package com.example.demo.dto;

import com.example.demo.domain.A;
import com.example.demo.domain.A_a;
import com.example.demo.domain.A_b;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class A_aResponse extends AResponse {
	private Long id;
	private String titleName;
	private String name;

	public A_aResponse(Long id, String titleName, String name) {
		this.id = id;
		this.titleName = titleName;
		this.name = name;
	}

	public static AResponse from(A a) {
		A_a a_a = (A_a)a;
		return new A_aResponse(a_a.getId(), a_a.getTitleName(), a_a.getName());
	}

}
