package get.to.the.point.pilot01.controller;

import get.to.the.point.pilot01.Service.MemberService;
import get.to.the.point.pilot01.dto.ResponseGetMember;
import get.to.the.point.pilot01.entity.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 가입
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) //  201 Created 반환
    public String registerMember(@RequestBody Member member) {
        memberService.registerMember(member);
        return "회원 가입 성공";
    }

    // 회원 조회 (ID 기준)
    @GetMapping("/{id}")
    public ResponseEntity<ResponseGetMember> getMemberById(@PathVariable Long id) {
        Optional<ResponseGetMember> member = memberService.getMemberById(id);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 회원 조회 (조건부)
    @GetMapping
    public ResponseEntity<?> getMembers(@RequestParam(required = false) String email) {
        if (email != null && !email.isBlank()) {
            // 특정 이메일 회원 조회
            Optional<Member> member = memberService.getMemberByEmail(email);
            return member.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
        // 전체 회원 조회
        List<Member> members = memberService.getAllMembers();
        if (members.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(members);
    }

    // 회원 탈퇴 (ID 기준)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return ResponseEntity.ok("회원 삭제 성공");
    }

    // 회원 탈퇴 (이메일 기준)
    @DeleteMapping("/email/{email}")
    public ResponseEntity<String> deleteMemberByEmail(@PathVariable String email) {
        memberService.deleteMemberByEmail(email);
        return ResponseEntity.ok("회원 삭제 성공");
    }

    // 회원 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Long id, @RequestBody Member member) {
        member.setId(id);
        memberService.updateMember(member);
        return ResponseEntity.ok("회원 수정 성공");
    }
}
