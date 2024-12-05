package get.to.the.point.pilot01.Service;

import get.to.the.point.pilot01.dto.CreateMemberDto;
import get.to.the.point.pilot01.dto.MemberVo;
import get.to.the.point.pilot01.dto.UpdateMemberDto;
import get.to.the.point.pilot01.mapper.MemberMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public Boolean insertMember(CreateMemberDto user) {
        try {
            this.memberMapper.insertMember(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public MemberVo selectMember(Long id) {
        return this.memberMapper.findMemberById(id);
    }

    public Boolean updateMember(Long id, UpdateMemberDto user) {
        try {
            this.memberMapper.updateMember(id, user);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
