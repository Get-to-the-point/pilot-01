package get.to.the.point.pilot01.Service;

import get.to.the.point.pilot01.entity.Member;
import get.to.the.point.pilot01.mapper.MemberMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @MockBean
    private MemberMapper memberMapper;

    @Test
    @DisplayName("회원 하나 넣어보기")
    void insertMember() {
        // 가짜 동작 설정
        BDDMockito.given(memberMapper.insertMember(any(Member.class))).willReturn(1);

        Member member = new Member();
        member.setName("킹태희");
        member.setEmail("1234");
        member.setPassword("123");
        int result = memberService.insertMember(member);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void getMemberById() {
        Member member = memberService.getMemberById(1L);

        assertThat(member.getId()).isEqualTo(1L);
    }
}