package get.to.the.point.pilot01.mapper;

import get.to.the.point.pilot01.dto.CreateMemberDto;
import get.to.the.point.pilot01.dto.MemberVo;
import get.to.the.point.pilot01.dto.UpdateMemberDto;
import get.to.the.point.pilot01.entity.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO member (email, password, name) VALUES (#{email}, #{password}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertMember(Member Member);

    @Select("SELECT * FROM member WHERE id = #{id}")
    Member selectMemberById(Long id);

    @Select("SELECT * FROM member WHERE email = #{email}")
    Member selectMemberByEmail(String email);

    @Select("SELECT * FROM member")
    List<Member> selectAll();
}
