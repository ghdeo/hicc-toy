package hicc.toy.repository;

import hicc.toy.domain.aritcle.Article;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    /*
     * 게시글 리스트 조회 - (삭제 여부 기준)
     * */
    List<Article> findAllByDeleteYn(final char deleteYn, final Sort sort);
}
