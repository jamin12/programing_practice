package com.example.firstproject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.example.firstproject.entitiy.Article;
import com.example.firstproject.entitiy.Comment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.web.WebAppConfiguration;

@DataJpaTest // jpa와 연동한 테스트!
@WebAppConfiguration
public class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void testFindByArticleId() {
        /* 케이스1: 4번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 4L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상하기
            // Article article = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
            // Comment a = new Comment(1L, article, "Park", "굳 윌 헌팅");
            // Comment b = new Comment(2L, article, "Kim", "아이 엠 샘");
            // Comment c = new Comment(3L, article, "Choi", "쇼생크의 탈출");
            // List<Comment> expected = Arrays.asList(a, b, c);
            // 검증
            // assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }
        /* 케이스1: 1번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            Long articleId = 1L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상하기
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모듯 댓글 조회")
    void testFindByNickname() {
        /* Case 1: Park의 모든 댓글 조회 */
        {
            // 입력 데이터 준비
            String nickname = "Park";
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기
            // Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ"), "Park",
            // "굳 윌 헌팅");
            // Comment b = new Comment(4L, new Article(5L, "당신의 인생 음식은?", "댓글 ㄱ"), "Park",
            // "치킨");
            // Comment c = new Comment(7L, new Article(6L, "당신의 인생 취미는?", "댓글 ㄱ"), "Park",
            // "조깅");
            // List<Comment> expected = Arrays.asList(a, b, c);
            // 검증
            // assertEquals(expected, comments, "Park의 모듯 댓글을 출력");
        }
    }
}
