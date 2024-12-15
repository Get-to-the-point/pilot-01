package get.to.the.point.pilot01.mapper;

import get.to.the.point.pilot01.entity.Board;
import get.to.the.point.pilot01.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // MySQL 사용
@Transactional
class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private MemberMapper memberMapper;

    private Member testMember;

    @BeforeEach
    void setUp() {
        testMember = new Member(null, (int)(Math.random() *10000) +"@example.com", "Test User", "password123");
        memberMapper.insertMember(testMember);
    }

    @Test
    void insertBoardTest() {
        Board board = new Board();
        board.setTitle("Test Title");
        board.setContent("Test Content");
        board.setMemberId(testMember.getId());
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        boardMapper.insertBoard(board);
        assertThat(board.getId()).isNotNull();
    }

    @Test
    void selectBoardByIdTest() {
        Board board = new Board();
        board.setTitle("Test Title");
        board.setContent("Test Content");
        board.setMemberId(testMember.getId());
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        boardMapper.insertBoard(board);
        Board foundBoard = boardMapper.selectBoardById(board.getId());
        assertThat(foundBoard).isNotNull();
        assertThat(foundBoard.getTitle()).isEqualTo("Test Title");
    }

    @Test
    void selectAllBoardsTest() {
        Board board1 = new Board();
        board1.setTitle("Title 1");
        board1.setContent("Content 1");
        board1.setMemberId(testMember.getId());
        board1.setCreatedAt(LocalDateTime.now());
        board1.setUpdatedAt(LocalDateTime.now());

        Board board2 = new Board();
        board2.setTitle("Title 2");
        board2.setContent("Content 2");
        board2.setMemberId(testMember.getId());
        board2.setCreatedAt(LocalDateTime.now());
        board2.setUpdatedAt(LocalDateTime.now());

        boardMapper.insertBoard(board1);
        boardMapper.insertBoard(board2);

        List<Board> boards = boardMapper.selectAllBoards();
        assertThat(boards).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void updateBoardByIdTest() {
        Board board = new Board();
        board.setTitle("Old Title");
        board.setContent("Old Content");
        board.setMemberId(testMember.getId());
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        boardMapper.insertBoard(board);

        board.setTitle("New Title");
        board.setContent("New Content");
        board.setUpdatedAt(LocalDateTime.now());
        boardMapper.updateBoardById(board);

        Board updatedBoard = boardMapper.selectBoardById(board.getId());
        assertThat(updatedBoard.getTitle()).isEqualTo("New Title");
        assertThat(updatedBoard.getContent()).isEqualTo("New Content");
    }

    @Test
    void deleteBoardByIdTest() {
        Board board = new Board();
        board.setTitle("Delete Title");
        board.setContent("Delete Content");
        board.setMemberId(testMember.getId());
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        boardMapper.insertBoard(board);

        boardMapper.deleteBoardById(board.getId());
        Board deletedBoard = boardMapper.selectBoardById(board.getId());
        assertThat(deletedBoard).isNull();
    }

    @Test
    void deleteBoardsByMemberIdTest() {
        Board board1 = new Board();
        board1.setTitle("Title 1");
        board1.setContent("Content 1");
        board1.setMemberId(testMember.getId());
        board1.setCreatedAt(LocalDateTime.now());
        board1.setUpdatedAt(LocalDateTime.now());

        Board board2 = new Board();
        board2.setTitle("Title 2");
        board2.setContent("Content 2");
        board2.setMemberId(testMember.getId());
        board2.setCreatedAt(LocalDateTime.now());
        board2.setUpdatedAt(LocalDateTime.now());

        boardMapper.insertBoard(board1);
        boardMapper.insertBoard(board2);

        boardMapper.deleteBoardsByMemberId(testMember.getId());

        List<Board> boards = boardMapper.selectBoardsByMemberId(testMember.getId());
        assertThat(boards).isEmpty();
    }
}
