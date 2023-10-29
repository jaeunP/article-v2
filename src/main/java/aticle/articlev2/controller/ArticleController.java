package aticle.articlev2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j  // 로깅을 위한 어노테이션
public class ArticleController {

    //index
    @GetMapping("/")
    public String index() {
        return "articles/index";
    }

    //new
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/articles/new")
    public String newArticle() {
        return "articles/new";
    }


    //show
    @GetMapping("/articles/{id}")
    public String show() {
        return "articles/show";
    }

    //edit
    @RequestMapping("/articles/edit/{id}")
    public String edit() {
        return "articles/edit";
    }


    @GetMapping("/login")
    public String login() {
        return "login/loginForm";
    }

    @GetMapping("/profile")
    public String profile(){ return "member/profile";}




//    //edit
//    @RequestMapping("/articles/edit/{id}")
//    public String edit() {
//        return "articles/edit.html";
//    }
//


}