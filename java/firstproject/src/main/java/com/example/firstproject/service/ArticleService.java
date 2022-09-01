package com.example.firstproject.service;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.FileDto;
import com.example.firstproject.entitiy.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.FileRepository;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 서비스 선언!(서비스 객체를 스프링부트에 생성 )
public class ArticleService {
    @Autowired // DI, 생성 객체를 가져와 연결
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntitiy();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1: 수정용 엔티티 생성
        Article article = dto.toEntitiy();
        log.info("id :{}, article {}", id, article.toString());

        // 2: 대상 엔티티 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3: 잘못된 요청 처리
        if (target == null || id != article.getId()) {
            // 400 응답
            log.info("잘못된 요청! id :{}, article {}", id, article.toString());
            return null;
        }
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        // 1: 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청 처리
        if (target == null) {
            return null;
        }
        // 2: 대상 삭제
        articleRepository.delete(target);
        // 3: 대상 반환
        return target;
    }

    @Transactional // 해당 메소드를 트랜젝션으로 묶는다
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 entitiy묶음으로 전환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntitiy())
                .collect(Collectors.toList());
        // entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제 실패"));
        // 결과값 반환
        return articleList;
    }
}
