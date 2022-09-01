package com.example.firstproject.repository;

import java.util.List;

import com.example.firstproject.entitiy.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회
    @Query(value = "Select * " +
            "from comment " +
            "where article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    // 특정 닉네임의 모든 댓글 조회
    @Query(value = "Select * from comment where nickname = :nickname", nativeQuery = true)
    List<Comment> findByNickname(@Param("nickname") String nickname);
}
