package com.example.firstproject.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.firstproject.entitiy.Article;
import com.example.firstproject.entitiy.FileE;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
    private Long id;

    // 게시글 번호
    private Long articleId;

    // 파일 이름
    private String fileName;

    // 파일 저장 경로
    private String filePath;

    // 파일 사이즈
    private Long fileSize;

    public static FileDto createfileDto(FileE file) {
        return new FileDto(
                file.getId(),
                file.getArticleId().getId(),
                file.getFileName(),
                file.getFilePath(),
                file.getFileSize());
    }

    public FileDto(Long articleId, String fileName, String filePath, Long fileSize) {
        this.articleId = articleId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

}
