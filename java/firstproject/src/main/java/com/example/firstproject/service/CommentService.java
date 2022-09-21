package com.example.firstproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entitiy.Article;
import com.example.firstproject.entitiy.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 조회: 댓글 목록
        // List<Comment> commnets = commentRepository.findByArticleId(articleId);

        // 변화: 엔티티 -> dto
        // List<CommentDto> dtos = new ArrayList<CommentDto>();
        // for (int i = 0; i < commnets.size(); i++) {
        // Comment c = commnets.get(i);
        // CommentDto dto = CommentDto.createComment(c);
        // dtos.add(dto);
        // }

        // 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
        // 댓글 엔티티 생성
        Comment commnet = Comment.createCommnet(dto, article);
        // 댓글 엔티티를 DB로 저장
        Comment create = commentRepository.save(commnet);
        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(create);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        // 댓글 수정
        target.patch(dto);
        // db갱신
        Comment updated = commentRepository.save(target);
        // 댓글 엔티티를 dto 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회(및 예외 발생)
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));
        // 댓글 db에서삭제
        commentRepository.delete(target);
        // 삭제 댓글을 dto로 변환
        return CommentDto.createCommentDto(target);
    }

}
