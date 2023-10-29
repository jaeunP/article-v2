package aticle.articlev2.repository;

import aticle.articlev2.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.ArrayList;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Override
    ArrayList<Article> findAll();
}
