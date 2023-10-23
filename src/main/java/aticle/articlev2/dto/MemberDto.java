package aticle.articlev2.dto;

import aticle.articlev2.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto {
    private Long memberId;
    private String username;
    private String password;
    private String nickname;
    private String tel;
    private Role role;

    @Builder

    public MemberDto(Long memberId, String username, String password, String nickname, String tel, Role role) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.tel = tel;
        this.role = role;
    }
}
