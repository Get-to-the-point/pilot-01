package get.to.the.point.pilot01.controller;

import get.to.the.point.pilot01.Service.MemberService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
