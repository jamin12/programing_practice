package com.example.firstproject.objectmapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

public class BurgerTest {
    @Test
    void testToString() throws JsonProcessingException {
        // 준비
        ObjectMapper objectmapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("통새우 패티", "순쇠고기 패티", "토마토", "스파이시 어니언 소스");
        Object buger = new Burger("맥도날드 슈비버거", 5500, ingredients);
        // 수행
        String json = objectmapper.writeValueAsString(buger);
        // 예상
        String expected = "{\"name\":\"맥도날드 슈비버거\",\"price\":5500,\"ingredients\":[\"통새우 패티\",\"순쇠고기 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";
        // 검증
        assertEquals(expected, json);
        JsonNode jsonNode = objectmapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    public void Jsontodto() throws JsonMappingException, JsonProcessingException {
        ObjectMapper objectmapper = new ObjectMapper();
        String json = "{\"name\":\"맥도날드 슈비버거\",\"price\":5500,\"ingredients\":[\"통새우 패티\",\"순쇠고기 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";

        Burger buger = objectmapper.readValue(json, Burger.class);

        List<String> ingredients = Arrays.asList("통새우 패티", "순쇠고기 패티", "토마토", "스파이시 어니언 소스");
        Object expected = new Burger("맥도날드 슈비버거", 5500, ingredients);

        assertEquals(expected, json);
    }
}
