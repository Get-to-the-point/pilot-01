package get.to.the.point.pilot01.mapper;

import get.to.the.point.pilot01.entity.Member;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // MySQL 사용
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    void insertMemberTest() {
        Member member = new Member(null, "test@example.com", "Test User", "password123");
        memberMapper.insertMember(member);
        assertThat(member.getId()).isNotNull();
    }

    @Test
    void selectMemberByIdTest() {
        Member member = new Member(null, "test2@example.com", "Test User 2", "password123");
        memberMapper.insertMember(member);
        Member foundMember = memberMapper.selectMemberById(member.getId());
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getEmail()).isEqualTo("test2@example.com");
    }

    @Test
    void selectAllMembersTest() {
        Member member1 = new Member(null, "test3@example.com", "Test User 3", "password123");
        Member member2 = new Member(null, "test4@example.com", "Test User 4", "password123");
        memberMapper.insertMember(member1);
        memberMapper.insertMember(member2);

        assertThat(memberMapper.selectAllMembers()).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void updateMemberByIdTest() {
        Member member = new Member(null, "update@example.com", "Before Update", "password123");
        memberMapper.insertMember(member);

        member.setName("After Update");
        memberMapper.updateMemberById(member);
        Member updatedMember = memberMapper.selectMemberById(member.getId());
        assertThat(updatedMember.getName()).isEqualTo("After Update");
    }

    @Test
    void deleteMemberByIdTest() {
        Member member = new Member(null, "delete@example.com", "To Delete", "password123");
        memberMapper.insertMember(member);

        memberMapper.deleteMemberById(member.getId());
        Member deletedMember = memberMapper.selectMemberById(member.getId());
        assertThat(deletedMember).isNull();
    }

    @Test
    void deleteMemberByEmailTest() {
        Member member = new Member(null, "deletebyemail@example.com", "To Delete by Email", "password123");
        memberMapper.insertMember(member);

        memberMapper.deleteMemberByEmail(member.getEmail());
        Member deletedMember = memberMapper.selectMemberByEmail(member.getEmail());
        assertThat(deletedMember).isNull();
    }
}
