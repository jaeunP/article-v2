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

    /**
     * Principal을 통하여 username 반환
     * username을 통하여 member 개체를 반환
     */
    public MemberDto getMember(String username) {
        Optional<Member> member = this.memberRepository.findByUsername(username);
        if (member.isPresent()) {
            MemberDto memberDto = toDto(member.get());

            return memberDto;
        } else {
            throw new RuntimeException("member가 존재하지 않습니다.");
        }
    }

    /**
     * 회원가입
     * memberDto -> member로 저장 -> memberDto
     */
    public Member signup(MemberDto memberDto) {
        if (memberRepository.findByUsername(memberDto.getUsername()).isPresent()) {
            throw new RuntimeException("이미 가입되어있는 유저입니다.");
        }
        if (memberDto.getUsername().isEmpty() && memberDto.getPassword().isEmpty())
            throw new RuntimeException("아이디 또는 비번을 입력해주세요.");

        Role role = Role.ROLE_MEMBER;

        Member member = Member.builder()
                .username(memberDto.getUsername())
                .nickname(memberDto.getNickname())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .role(role)
                .build();

        return memberRepository.save(member);
    }







    //Entity -> Dto
    public static MemberDto toDto(Member member) {
        MemberDto memberDto = MemberDto.builder()
                .username(member.getUsername())
                .nickname(member.getNickname())
                .password(member.getPassword())
                .build();
        return memberDto;
    }


}
