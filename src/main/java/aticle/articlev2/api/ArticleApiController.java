package aticle.articlev2.api;

import aticle.articlev2.dto.ArticleDto;
import aticle.articlev2.entity.Article;
import aticle.articlev2.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleApiController {

    private final ArticleService articleService;

    public ArticleApiController(ArticleService articleService) {
        this.articleService = articleService;
    }

    //게시글 전체 불러오기
    @GetMapping("/articles")
    public List<ArticleDto> index() {
        return articleService.index();
    }

//    @GetMapping("/articles/username")
//    public String currentUserName(@AuthenticationPrincipal UserImpl userImpl) {
//        String username = userImpl.getMember().getUsername();
//        return username;
//    }


    //게시글 상세 조회
    @GetMapping("/articles/{id}")
    public ArticleDto show(@PathVariable Long id) {
        return articleService.show(id);
    }


    //생성
    @PostMapping("/articles/new")
    public ResponseEntity<ArticleDto> create(@RequestBody ArticleDto dto) {
        ArticleDto createdDto = articleService.create(dto);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 수정
    @PatchMapping("/articles/{id}")
    public ResponseEntity<ArticleDto> update(@PathVariable Long id,
                                             @RequestBody ArticleDto dto,
                                             Principal principal) {

        ArticleDto updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //삭제
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
