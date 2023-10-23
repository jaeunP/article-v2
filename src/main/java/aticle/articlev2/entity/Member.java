package aticle.articlev2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public Member(Long memberId, String username, String password, Role role) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
