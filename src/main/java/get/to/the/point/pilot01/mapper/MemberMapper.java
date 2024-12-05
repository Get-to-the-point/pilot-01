package get.to.the.point.pilot01.mapper;

import get.to.the.point.pilot01.dto.CreateMemberDto;
import get.to.the.point.pilot01.dto.MemberVo;
import get.to.the.point.pilot01.dto.UpdateMemberDto;
import get.to.the.point.pilot01.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {
    // 직접 SQL을 작성하여 사용자 정보를 조회
    @Select("SELECT * FROM member WHERE is_active = true")
    List<Member> findAllMembers();

    @Select("SELECT * FROM member WHERE id = #{id}")
    MemberVo findMemberById(Long id);

    // MemberMapper.xml 파일에 SQL 정보가 있음
    MemberVo findMemberByEmail(String email);
    void insertMember(CreateMemberDto Member);
    void updateMember(Long id, UpdateMemberDto Member);
    void deleteMember(Long id);
}
