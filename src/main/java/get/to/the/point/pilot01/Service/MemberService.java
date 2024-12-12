package get.to.the.point.pilot01.Service;

import get.to.the.point.pilot01.entity.Member;
import get.to.the.point.pilot01.mapper.MemberMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public int insertMember(Member member) {
        System.out.println("=================================");
        System.out.println("===MemberService.insertMember===");
        System.out.println(member);
        System.out.println("===MemberService.insertMember===");
        System.out.println("=================================");
        return memberMapper.insertMember(member);
    }

    public Member getMemberById(Long id) {
        System.out.println("=================================");
        System.out.println("===MemberService.getMemberById===");
        Member member = memberMapper.selectMemberById(id);
        System.out.println(member);
        System.out.println("===MemberService.getMemberById===");
        System.out.println("=================================");
        return member;
    }
}
