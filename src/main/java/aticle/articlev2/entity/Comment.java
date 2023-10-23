package aticle.articlev2.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  //해당 댓글 Entity 여러개가, 하나의 Artilce에 연관된다.
    @JoinColumn(name = "article_id")    //"articleId" 컬럼에 Article의 대표값을 저장
    private Article article;

    private String nickname;

    private String body;

    @Builder
    public Comment(Long id, Article article, String nickname, String body) {
        this.id = id;
        this.article = article;
        this.nickname = nickname;
        this.body = body;
    }
}
