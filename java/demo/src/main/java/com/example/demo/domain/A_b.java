package com.example.demo.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class A_b extends A{
    private String name;
    private Long price;

    public A_b(Long id, String titleName, String name, Long price) {
        super(id, titleName);
        this.name = name;
        this.price = price;
    }
}
