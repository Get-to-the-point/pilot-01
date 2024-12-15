package get.to.the.point.pilot01.mapper;

import get.to.the.point.pilot01.entity.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    // CREATE
    @Insert("INSERT INTO board (title, content, member_id, created_at, updated_at) " +
            "VALUES (#{title}, #{content}, #{memberId}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertBoard(Board board);

    // READ - 단일 조회
    @Select("SELECT * FROM board WHERE id = #{id}")
    Board selectBoardById(Long id);

    // READ - 특정 회원의 게시글 조회
    @Select("SELECT * FROM board WHERE member_id = #{memberId}")
    List<Board> selectBoardsByMemberId(Long memberId);

    // READ - 전체 게시글 조회
    @Select("SELECT * FROM board")
    List<Board> selectAllBoards();

    // UPDATE
    @Update("UPDATE board SET title = #{title}, content = #{content}, updated_at = #{updatedAt} WHERE id = #{id}")
    void updateBoardById(Board board);

    // DELETE - 게시글 삭제
    @Delete("DELETE FROM board WHERE id = #{id}")
    void deleteBoardById(Long id);

    // DELETE - 특정 회원의 모든 게시글 삭제
    @Delete("DELETE FROM board WHERE member_id = #{memberId}")
    void deleteBoardsByMemberId(Long memberId);
}
