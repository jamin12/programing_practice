package com.example.firstproject.repository;

import java.util.List;

import com.example.firstproject.dto.FileDto;
import com.example.firstproject.entitiy.Article;
import com.example.firstproject.entitiy.FileE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FileRepository extends JpaRepository<FileE, Long> {
    List<FileE> findByArticleId(@Param("articleId") Article articleId);
}
