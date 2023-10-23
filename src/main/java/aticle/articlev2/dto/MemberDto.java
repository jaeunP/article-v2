package aticle.articlev2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {
    private String username;
    private String password;

    @Builder
    public MemberDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
