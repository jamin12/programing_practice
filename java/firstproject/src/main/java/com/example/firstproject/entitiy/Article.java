package com.example.firstproject.entitiy;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // db가 해당 객체를 인식 가능
@Getter
@ToString
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 args를 가진 생성자
public class Article {
    @Id // 대표 값을 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성 어노테이션
    private Long id;

    @Column
    private String title;
    @Column
    private String content;
    // @Column
    // private List<File> files;

    public void patch(Article article) {
        if (article.title != null) {
            this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }
    }
}
