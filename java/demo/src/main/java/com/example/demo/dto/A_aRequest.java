package com.example.demo.dto;

import com.example.demo.domain.A;
import com.example.demo.domain.A_a;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class A_aRequest extends AInterface {
	private String abc;

	public A_aRequest(Long id, String titleName, String name, String abc) {
		super(id, titleName, name);
		this.abc = abc;
	}

	@Override
	public A toA() {
		return new A_a(getId(), getTitleName(), getName());
	}
}
