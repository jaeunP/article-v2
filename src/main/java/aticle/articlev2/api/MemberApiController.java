package aticle.articlev2.api;

import aticle.articlev2.dto.MemberDto;
import aticle.articlev2.entity.Member;
import aticle.articlev2.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class MemberApiController {

    private final MemberService memberService;

    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/profile")
    public ResponseEntity<MemberDto> getMember(Principal principal) {
        String username = principal.getName();
        MemberDto getMember = memberService.getMember(username);
        return ResponseEntity.ok(getMember);
    }

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@RequestBody MemberDto memberDto) {
        Member member = memberService.signup(memberDto);
        return ResponseEntity.ok(member);
    }




}
