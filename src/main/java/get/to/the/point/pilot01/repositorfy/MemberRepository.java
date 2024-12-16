package get.to.the.point.pilot01.repositorfy;

import get.to.the.point.pilot01.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import get.to.the.point.pilot01.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    // JPA로 멤버와 연결된 보드 가져오기
    List<Board> findAllByMemberId(Long memberId);

    // 네이티브 쿼리도 작성
    @Query(value = "SELECT * FROM board WHERE member_id = :memberId", nativeQuery = true)
    List<Board> findAllByMemberIdQuery(Long memberId);
}
