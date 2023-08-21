package com.example.demo.dto;

import com.example.demo.domain.A;
import com.example.demo.domain.A_b;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class A_bRequest extends AInterface {
	private Long price;

	public A_bRequest(Long id, String titleName, String name, Long price) {
		super(id, titleName, name);
		this.price = price;
	}

	@Override
	public A toA() {
		return new A_b(getId(), getTitleName(), getName(), price);
	}
}
