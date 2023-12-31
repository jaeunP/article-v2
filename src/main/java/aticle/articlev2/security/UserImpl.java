package aticle.articlev2.security;

import aticle.articlev2.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;


@Slf4j
@Getter
@Setter
public class UserImpl extends User {

    private Member member;


    public UserImpl(Member member) {
        super(member.getUsername(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));

        this.member = member;
    }
}
