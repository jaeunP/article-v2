package aticle.articlev2.service;

import aticle.articlev2.dto.MemberDto;
import aticle.articlev2.entity.Member;
import aticle.articlev2.entity.Role;
import aticle.articlev2.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Member signup(MemberDto memberDto) {

        Role role = Role.ROLE_MEMBER;

        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .role(role)
                .build();

        return memberRepository.save(member);
    }
}
