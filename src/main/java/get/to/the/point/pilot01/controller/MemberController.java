package get.to.the.point.pilot01.controller;

import get.to.the.point.pilot01.Service.MemberService;
import get.to.the.point.pilot01.dto.CreateMemberDto;
import get.to.the.point.pilot01.dto.MemberVo;
import get.to.the.point.pilot01.dto.UpdateMemberDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/{id}")
    public MemberVo getMember(@PathVariable Long id) {
        return this.memberService.selectMember(id);
    }

    @PostMapping("/members")
    public Boolean createUser(CreateMemberDto user) {
        return memberService.insertMember(user);
    }

    @PutMapping("/members/{id}")
    public Boolean updateUser(@PathVariable Long id, UpdateMemberDto user) {
        return memberService.updateMember(id, user);
    }
}
