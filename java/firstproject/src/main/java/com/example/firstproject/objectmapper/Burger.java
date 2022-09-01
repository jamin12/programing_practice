package com.example.firstproject.objectmapper;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Burger {
    private String name;
    private int price;
    private List<String> ingredients;
}
