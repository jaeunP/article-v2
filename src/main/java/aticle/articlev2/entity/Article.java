package aticle.articlev2.entity;

import aticle.articlev2.dto.ArticleDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String title;

    private String content;

    @Builder
    public Article(Long id, Member member, String title, String content) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public void patch(ArticleDto dto) {
        if (dto.getTitle() != null)
            this.title = dto.getTitle();
        if (dto.getContent() != null)
            this.content = dto.getContent();
    }
}
