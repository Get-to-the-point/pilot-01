package get.to.the.point.pilot01.controller;

import get.to.the.point.pilot01.Service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/{id}")
    public String getMember(@PathVariable Long id) {
        return memberService.getMemberById(id).toString();
    }

}
