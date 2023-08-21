package com.example.demo.dto;

import com.example.demo.domain.A;
import com.example.demo.domain.A_a;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME,
	property = "type"
)
@JsonSubTypes(value = {
	@JsonSubTypes.Type(value = A_aResponse.class, name = "A_a"),
	@JsonSubTypes.Type(value = A_bResponse.class, name = "A_b")
})
public abstract class AResponse {
	public static AResponse getA(A a) {
		if (a instanceof A_a) {
			return A_aResponse.from(a);
		} else {
			return A_bResponse.from(a);
		}
	}
}
