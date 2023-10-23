package aticle.articlev2.security;

import aticle.articlev2.entity.Member;
import aticle.articlev2.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component

public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    public UserDetailsServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> findMember = memberRepository.findByUsername(username);

        if (!findMember.isPresent()) throw new UsernameNotFoundException("존재하지 않는 username 입니다");

        return new UserImpl(findMember.get());
    }
}
