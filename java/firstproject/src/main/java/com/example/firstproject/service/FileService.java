package com.example.firstproject.service;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import com.example.firstproject.dto.FileDto;
import com.example.firstproject.entitiy.Article;
import com.example.firstproject.entitiy.FileE;
import com.example.firstproject.repository.FileRepository;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Transactional
    public void createFile(Article articleId, MultipartHttpServletRequest multipartHttpServletRequest)
            throws IllegalStateException, IOException {
        if (ObjectUtils.isEmpty(multipartHttpServletRequest)) {
            return;
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        ZonedDateTime current = ZonedDateTime.now();
        String path = "c:/Users/jamin/Desktop/" + current.format(format);
        File file = new File(path);
        if (file.exists() == false) {
            file.mkdirs();
        }

        Iterator<String> FileIt = multipartHttpServletRequest.getFileNames();
        String name;
        String newFileName, originalFileextension;
        while (FileIt.hasNext()) {
            name = FileIt.next();
            List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
            for (MultipartFile multipartFile : list) {
                // 파일 dto 생성
                FileDto fileDto = new FileDto(articleId.getId(),
                        multipartFile.getOriginalFilename(),
                        path,
                        multipartFile.getSize());
                // repository 사용해 db에 저장
                fileRepository.save(FileE.cretaeFile(fileDto, articleId));
                originalFileextension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

                newFileName = Long.toString(System.nanoTime()) + "." + originalFileextension;

                file = new File(path + "/" + newFileName);
                multipartFile.transferTo(file);
            }

        }
    }

    public List<FileDto> ShowFileById(Article articleId) {
        List<FileE> files = fileRepository.findByArticleId(articleId);
        List<FileDto> list = new ArrayList<>();
        for (FileE file : files) {
            list.add(FileDto.createfileDto(file));
        }
        return list;
    }
}
