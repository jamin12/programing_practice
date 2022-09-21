package com.example.firstproject.repository;

import java.util.ArrayList;

import com.example.firstproject.entitiy.Article;

import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}
