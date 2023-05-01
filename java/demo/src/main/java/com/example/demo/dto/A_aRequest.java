package com.example.demo.dto;

import com.example.demo.domain.A;
import com.example.demo.domain.A_a;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class A_aRequest extends AInterface{
    private Long id;
    private String titleName;
    private String name;

    public A_aRequest(Long id, String titleName, String name) {
        this.id = id;
        this.titleName = titleName;
        this.name = name;
    }

    @Override
    public A toA() {
        return new A_a(id,titleName,name);
    }
}
