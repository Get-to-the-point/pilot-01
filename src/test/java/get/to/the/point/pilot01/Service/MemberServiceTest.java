package get.to.the.point.pilot01.Service;

import get.to.the.point.pilot01.dto.ResponseGetMember;
import get.to.the.point.pilot01.entity.Member;
import get.to.the.point.pilot01.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @MockBean
    private MemberMapper memberMapper;

    private Member testMember;

    @BeforeEach
    void setUp() {
        testMember = new Member(1L, "test@example.com", "Test User", "password123");
    }

    @Test
    void registerMemberTest() {
        memberService.registerMember(testMember);
        Mockito.verify(memberMapper).insertMember(testMember);
    }

    @Test
    void getMemberByIdTest() {
        given(memberMapper.selectMemberById(1L)).willReturn(testMember);
        Optional<ResponseGetMember> member = memberService.getMemberById(1L);
        assertThat(member).isPresent();
        assertThat(member.get().getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void getMemberByEmailTest() {
        given(memberMapper.selectMemberByEmail("test@example.com")).willReturn(testMember);
        Optional<Member> member = memberService.getMemberByEmail("test@example.com");
        assertThat(member).isPresent();
        assertThat(member.get().getName()).isEqualTo("Test User");
    }

    @Test
    void getAllMembersTest() {
        given(memberMapper.selectAllMembers()).willReturn(List.of(testMember));
        List<Member> members = memberService.getAllMembers();
        assertThat(members).isNotEmpty();
        assertThat(members).hasSize(1);
    }

    @Test
    void deleteMemberByIdTest() {
        memberService.deleteMemberById(1L);
        Mockito.verify(memberMapper).deleteMemberById(1L);
    }

    @Test
    void deleteMemberByEmailTest() {
        memberService.deleteMemberByEmail("test@example.com");
        Mockito.verify(memberMapper).deleteMemberByEmail("test@example.com");
    }

    @Test
    void updateMemberTest() {
        given(memberMapper.selectMemberById(1L)).willReturn(testMember);
        Member updatedMember = new Member(1L, "updated@example.com", "Updated User", "newpassword");
        memberService.updateMember(updatedMember);
        Mockito.verify(memberMapper).updateMemberById(updatedMember);
    }

    @Test
    void updateNonExistingMemberTest() {
        given(memberMapper.selectMemberById(1L)).willReturn(null);
        Member nonExistingMember = new Member(1L, "nonexisting@example.com", "Non Existing", "nopassword");
        assertThrows(IllegalArgumentException.class, () -> memberService.updateMember(nonExistingMember));
    }

    @Test
    void jpaInsertMemberTest() {
        testMember.setEmail("123456");
        memberService.jpaInsertMember(testMember);

        assertThat(testMember.getId()).isNotNull();
    }

}
