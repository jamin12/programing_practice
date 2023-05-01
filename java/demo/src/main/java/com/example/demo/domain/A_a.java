package com.example.demo.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class A_a extends A{
    private String name;

    public A_a(Long id, String titleName, String name) {
        super(id, titleName);
        this.name = name;
    }
}
