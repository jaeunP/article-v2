package aticle.articlev2.service;

import aticle.articlev2.dto.ArticleDto;
import aticle.articlev2.entity.Article;
import aticle.articlev2.entity.Member;
import aticle.articlev2.repository.ArticleRepository;
import aticle.articlev2.repository.MemberRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public ArticleService(ArticleRepository articleRepository, MemberRepository memberRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
    }

    public Article getArticle(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            return article.get();
        } else {
            throw new RuntimeException("Article이 존재하지 않습니다.");
        }
    }



    //전체 목록 조회
    public List<ArticleDto> index() {

        List<Article> articleList = articleRepository.findAll();

        return articleList
                .stream()
                .map(article -> toDto(article))
                .collect(Collectors.toList());
    }

    //상세 조회
    public ArticleDto show(Long id) {
        ArticleDto detail = toDto( articleRepository.findById(id).orElse(null));
        return detail;
    }

    //생성
    @Transactional
    public ArticleDto create(ArticleDto articleDto) {

        Article article = toEntity(articleDto);
        if(article.getId() != null) {
            return null;
        }

        Article created = articleRepository.save(article);

        return toDto(created);
    }

    //수정
    @Transactional
    public ArticleDto update(Long id, ArticleDto dto) {

        Article target = articleRepository.findById(id).orElse(null);

        target.patch(dto);

        Article updated = articleRepository.save(target);

        //Dto로 변환
        return toDto(updated);
    }

    //삭제
    @Transactional
    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        articleRepository.delete(target);
        return target;
    }

    public static ArticleDto toDto(Article article) {

        ArticleDto articleDto = ArticleDto.builder()
                .id(article.getId())
                .username(article.getMember().getUsername())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
        return articleDto;
    }

    public Article toEntity(ArticleDto articleDto) {
        String username = getCurrentUsername();
        Optional<Member> member = memberRepository.findByUsername(username);


        Article article = Article.builder()
                .id(articleDto.getId())
                .member(member.get())
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .build();

        return article;
    }

    public static String getCurrentUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails)principal;
        String username = userDetails.getUsername();
        return username;
    }




}
//    public static ArticleDto toDto(Article article) {
//        return new ArticleDto(
//                article.getId(),
//                article.getTitle(),
//                article.getContent()
//        );
//    }

//    public static Article toEntity(ArticleDto dto) {
//        return new Article(
//                dto.getId(),
//                dto.getTitle(),
//                dto.getContent()
//        );
//    }