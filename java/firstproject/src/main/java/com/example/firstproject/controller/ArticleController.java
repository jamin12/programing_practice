package com.example.firstproject.controller;

import java.util.List;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.dto.FileDto;
import com.example.firstproject.entitiy.Article;
import com.example.firstproject.entitiy.FileE;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import com.example.firstproject.service.CommentService;
import com.example.firstproject.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // 로깅을 위한 골뱅이다
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private FileService fileService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form, MultipartHttpServletRequest multipartHttpServletRequest)
            throws Exception {
        log.info(form.toString());
        // System.out.println(form.toString()); -- > 로깅기능으로 대체

        // 1. dto 변환 entitiy
        Article article = form.toEntitiy();
        // System.out.println(article.toString());
        log.info(article.toString());

        // 2. repository에게 entitiy를 db안에 저장하게 함
        Article saved = articleService.create(form);

        fileService.createFile(article, multipartHttpServletRequest);
        // System.out.println(saved.toString());
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1: id로 데이터를 가져옴!
        Article articelEntitiy = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);
        List<FileDto> fileDto = fileService.ShowFileById(articelEntitiy);
        // 2: 가져온 데이터를 모델에 등록!
        model.addAttribute("article", articelEntitiy);
        model.addAttribute("commentDtos", commentDtos);
        model.addAttribute("file", fileDto);
        // 3: 보여줄 페이지를 설정!
        return "articles/show";
    }

    @GetMapping(value = "/articles")
    public String index(Model model) {
        // 1: 모든 article을 가져온다
        java.util.List<Article> articleEntitiyList = articleRepository.findAll();

        // 2: 가져온 article 묶음을 뷰로 전달!
        model.addAttribute("articleList", articleEntitiyList);
        // 3: 뷰 페이지를 설정!
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터를 가져오기!
        Article articleEntitiy = articleRepository.findById(id).orElse(null);
        // 모델에 데이터를 등록!
        model.addAttribute("article", articleEntitiy);
        // 뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        // 1: DTO를 엔티티로 변환한다!
        Article articleEntitiy = form.toEntitiy();
        log.info(articleEntitiy.toString());
        // 2: 엔티티를 db로 저장한다
        // 2-1: db에서 기존 데이터를 가져온다!
        Article target = articleRepository.findById(articleEntitiy.getId()).orElse(null);
        // 2-2: 기존 데이터에 값을 갱신한다!
        if (target != null) {
            articleRepository.save(articleEntitiy); // 엔티티 저장
        }
        // 3: 수정 겨로가를 페이지로 리다이렉트 한다
        return "redirect:/articles/" + articleEntitiy.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        // 1: 삭제대상을 가져온다
        Article target = articleRepository.findById(id).orElse(null);

        // 2: 대상을 삭제한다
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다!");
        }

        // 3: 결과 페이지로 리다이렉트
        return "redirect:/articles/";
    }
}
