package com.example.firstproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entitiy.Article;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅 된다
public class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void testIndex() {
        // 예상
        // Article a = new Article(1L, "qwer", "1111");
        // Article b = new Article(2L, "asdf", "2222");
        // Article c = new Article(3L, "zxcv", "3333");
        // List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        // 실제
        List<Article> articles = articleService.index();
        // 비교
        // assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void test_show_success() {
        Long id = 1L;
        // 예상
        // Article expected = new Article(1L, "qwer", "1111");
        // 실제
        Article article = articleService.show(id);

        // 비교
        // assertEquals(expected.toString(), article.toString());
    }

    @Test
    void test_show_failed() {
        Long id = -1L;
        // 예상
        Article expected = null;
        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_success() {
        String title = "라라라라";
        String content = "4444";
        // 예상
        // ArticleForm dto = new ArticleForm(null, title, content);
        // Article expected = new Article(4L, title, content);
        // 실제
        // Article article = articleService.create(dto);/

        // 비교
        // assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_failed() {
        String title = "라라라라";
        String content = "4444";
        // 예상
        // ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;
        // 실제
        // Article article = articleService.create(dto);

        // 비교
        // assertEquals(expected, article);
    }
}
