package com.example.demo.dto;

import com.example.demo.domain.A;
import com.example.demo.domain.A_a;
import com.example.demo.domain.A_b;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class A_bResponse extends AResponse {
	private Long id;
	private String titleName;
	private String name;
	private Long price;

	public A_bResponse(Long id, String titleName, String name, Long price) {
		this.id = id;
		this.titleName = titleName;
		this.name = name;
		this.price = price;
	}

	public static AResponse from(A a) {
		A_b a_b = (A_b)a;
		return new A_bResponse(a_b.getId(), a_b.getTitleName(), a_b.getName(), a_b.getPrice());
	}
}
