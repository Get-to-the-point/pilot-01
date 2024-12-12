package get.to.the.point.pilot01.mapper;

import get.to.the.point.pilot01.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // MySQL 사용
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    void insertMember() {
        Member member = new Member();
        member.setName("abc");
        member.setEmail("abc");
        member.setPassword("123");
        memberMapper.insertMember(member);
    }

    @Test
    void selectMemberById() {
        Member member = new Member();
        member.setName("abc");
        member.setEmail("efg");
        member.setPassword("123");
        memberMapper.insertMember(member);

        Member selectedMember = memberMapper.selectMemberById(member.getId());
        System.out.println(selectedMember);
    }
}
