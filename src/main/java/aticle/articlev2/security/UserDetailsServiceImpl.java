package aticle.articlev2.security;

import aticle.articlev2.entity.Member;
import aticle.articlev2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> findMember = memberRepository.findByUsername(username);

        if (findMember.isEmpty()) throw new UsernameNotFoundException("존재하지 않는 username 입니다");

        log.info("loadUserByUsername member.username = {}", username);

        return new UserImpl(findMember.get());
    }
}
