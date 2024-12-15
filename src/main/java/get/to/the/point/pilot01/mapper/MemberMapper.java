package get.to.the.point.pilot01.mapper;

import get.to.the.point.pilot01.entity.Member;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface MemberMapper {

    // CREATE
    @Insert("INSERT INTO member (email, name, password) VALUES (#{email}, #{name}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertMember(Member member);

    // READ - 단일 조회
    @Select("SELECT * FROM member WHERE id = #{id}")
    Member selectMemberById(Long id);

    @Select("SELECT * FROM member WHERE email = #{email}")
    Member selectMemberByEmail(String email);

    // READ - 전체 조회
    @Select("SELECT * FROM member")
    List<Member> selectAllMembers();

    // UPDATE
    @Update("UPDATE member SET email = #{email}, name = #{name}, password = #{password} WHERE id = #{id}")
    void updateMemberById(Member member);

    // DELETE
    @Delete("DELETE FROM member WHERE id = #{id}")
    void deleteMemberById(Long id);

    @Delete("DELETE FROM member WHERE email = #{email}")
    void deleteMemberByEmail(String email);
}
