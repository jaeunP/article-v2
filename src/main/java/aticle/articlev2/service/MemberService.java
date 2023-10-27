package aticle.articlev2.service;

import aticle.articlev2.dto.MemberDto;
import aticle.articlev2.entity.Member;
import aticle.articlev2.entity.Role;
import aticle.articlev2.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Member signup(MemberDto memberDto) {
        if(memberRepository.findByUsername(memberDto.getUsername()).isPresent()){
            throw new RuntimeException("이미 가입되어있는 유저입니다.");
        } if(memberDto.getUsername().isEmpty() && memberDto.getPassword().isEmpty()) throw new RuntimeException("아이디 또는 비번을 입력해주세요.");

        Role role = Role.ROLE_MEMBER;

        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .role(role)
                .build();

        return memberRepository.save(member);
    }

    public Member getMember(String username) {
        Optional<Member> member = this.memberRepository.findByUsername(username);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new RuntimeException("member가 존재하지 않습니다.");
        }
    }

}
