package aticle.articlev2.controller;

import aticle.articlev2.repository.ArticleRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j  // 로깅을 위한 어노테이션
public class ArticleController {

    @Autowired  //Spring Boot가 미리 생성해놓은 객체를 가져다가 연결
    private ArticleRepository articleRepository;

//    @Autowired
//    private CommentService commentService;

    //index
    @GetMapping("/")
    public String index() {
        return "articles/index.html";
    }

    //new
    @GetMapping("/articles/new")
    public String newArticle() {
        return "articles/new.html";
    }


    //show
    @GetMapping("/articles/{id}")
    public String show() {
        return "articles/show.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login/loginForm.html";
    }


//    //edit
//    @RequestMapping("/articles/edit/{id}")
//    public String edit() {
//        return "articles/edit.html";
//    }
//


}