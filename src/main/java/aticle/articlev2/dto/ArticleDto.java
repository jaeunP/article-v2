package aticle.articlev2.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ArticleDto {

    private Long id;
    private String username;
    private String title;
    private String content;


    @Builder
    public ArticleDto(Long id, String username, String title, String content) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
    }
}
