package get.to.the.point.pilot01.Service;

import get.to.the.point.pilot01.dto.ResponseGetMember;
import get.to.the.point.pilot01.entity.Member;
import get.to.the.point.pilot01.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    // 회원 가입
    public void registerMember(Member member) {
        memberMapper.insertMember(member);
    }

    // 회원 조회 (ID로 단일 조회)
    public Optional<ResponseGetMember> getMemberById(Long id) {
        Optional<Member> foundMember = Optional.ofNullable(memberMapper.selectMemberById(id));
        return foundMember.map(member -> new ResponseGetMember(member.getId(), member.getEmail(), member.getName()));
    }

    // 회원 조회 (이메일로 단일 조회)
    public Optional<Member> getMemberByEmail(String email) {
        return Optional.ofNullable(memberMapper.selectMemberByEmail(email));
    }

    // 전체 회원 조회
    public List<Member> getAllMembers() {
        return memberMapper.selectAllMembers();
    }

    // 회원 탈퇴 (ID 기준)
    public void deleteMemberById(Long id) {
        memberMapper.deleteMemberById(id);
    }

    // 회원 탈퇴 (이메일 기준)
    public void deleteMemberByEmail(String email) {
        memberMapper.deleteMemberByEmail(email);
    }

    // 회원 정보 수정
    public void updateMember(Member member) {
        Member existingMember = memberMapper.selectMemberById(member.getId());
        if (existingMember == null) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }
        memberMapper.updateMemberById(member);
    }
}
