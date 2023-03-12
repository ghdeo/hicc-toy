package hicc.toy.repository;

import hicc.toy.domain.board.Board;

import java.util.List;

public interface BoardRepository {
    void create(Board board);
    void update(Long boardId);
    void delete(Long boardId);
    Board findByMemberId(Long memberId);
    List<Board> findByTitle(String keyword);
    List<Board> findAll();

}
