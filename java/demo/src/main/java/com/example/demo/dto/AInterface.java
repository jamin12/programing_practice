package com.example.demo.dto;

import com.example.demo.domain.A;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = A_aRequest.class, name = "A_a"),
        @JsonSubTypes.Type(value = A_bRequest.class, name = "A_b")
})
public class AInterface {
    private String a;
    private String b;

    public A toA() {
        return null;
    }
}
