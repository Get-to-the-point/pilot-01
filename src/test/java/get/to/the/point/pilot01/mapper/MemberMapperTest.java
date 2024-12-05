package get.to.the.point.pilot01.mapper;

import get.to.the.point.pilot01.dto.CreateMemberDto;
import get.to.the.point.pilot01.dto.MemberVo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import static org.junit.jupiter.api.Assertions.assertTrue;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // MySQL 사용
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PlatformTransactionManager transactionManager; // 트랜잭션 매니저


    private final String email = Math.random() + "@email.com";
    private final String password = "password";
    private final String name = "king";

    @Test
    void insertMember() {
        CreateMemberDto createMemberDto = new CreateMemberDto(email, password, name);
        this.memberMapper.insertMember(createMemberDto);
    }

    @Test
    void findMemberById() {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());

        CreateMemberDto createMember = new CreateMemberDto(email, password, name);
        this.memberMapper.insertMember(createMember);
        transactionManager.commit(transaction);
        MemberVo findMember = this.memberMapper.findMemberById(createMember.getId());

        assertTrue(findMember.getEmail().equals(createMember.getEmail()));
        assertTrue(findMember.getName().equals(createMember.getName()));
        assertTrue(findMember.getIsActive());
        assertTrue(findMember.getCreatedAt() != null);
        assertTrue(findMember.getUpdatedAt() != null);
    }

    @Test
    @Commit
    void findMemberByEmail() {
        CreateMemberDto createMember = new CreateMemberDto(email, password, name);
        this.memberMapper.insertMember(createMember);
        MemberVo findMember = this.memberMapper.findMemberByEmail(createMember.getEmail());

        assertTrue(findMember.getEmail().equals(createMember.getEmail()));
        assertTrue(findMember.getName().equals(createMember.getName()));
//        assertTrue(findMember.getIsActive());
//        assertTrue(findMember.getCreatedAt() != null);
//        assertTrue(findMember.getUpdatedAt() != null);
    }
}
