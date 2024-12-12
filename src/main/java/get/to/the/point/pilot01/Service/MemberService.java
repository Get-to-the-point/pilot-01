package get.to.the.point.pilot01.Service;

import get.to.the.point.pilot01.mapper.MemberMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }
}
