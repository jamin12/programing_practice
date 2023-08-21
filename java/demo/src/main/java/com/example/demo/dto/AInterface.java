package com.example.demo.dto;

import com.example.demo.domain.A;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	property = "type"
)
@JsonSubTypes(value = {
	@JsonSubTypes.Type(value = A_aRequest.class, name = "A_a"),
	@JsonSubTypes.Type(value = A_bRequest.class, name = "A_b")
})
@Getter
@NoArgsConstructor
public abstract class AInterface {
	private Long id;
	private String titleName;
	private String name;

	protected AInterface(Long id, String titleName, String name) {
		this.id = id;
		this.titleName = titleName;
		this.name = name;
	}

	public abstract A toA();

}
