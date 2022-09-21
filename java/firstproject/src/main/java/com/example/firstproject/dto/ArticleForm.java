package com.example.firstproject.dto;

import java.util.List;

import com.example.firstproject.entitiy.Article;
import com.example.firstproject.entitiy.FileE;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ArticleForm {

    private Long id;
    private String title;
    private String content;
    private List<FileDto> files;

    public Article toEntitiy() {
        return new Article(id, title, content);
    }
}
