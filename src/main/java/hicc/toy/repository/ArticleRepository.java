package hicc.toy.repository;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.domain.aritcle.ArticleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    /*
     * 게시글 리스트 조회 - (게시글 종류, 삭제 여부 기준)
     * */
    @Query("SELECT a FROM Article a WHERE a.articleType = :articleType AND a.isDeleted = :isDeleted")
    Page<Article> findByArticleTypeAndDeleteYn(ArticleType articleType, boolean isDeleted, Pageable pageable);

    /*
     * 게시글 검색 - (게시글 종류, 제목 기준)
     * */
    Page<Article> findByTitleContainingAndArticleTypeAndDeleteYn(String title, ArticleType articleType, boolean isDeleted, Pageable pageable);

}
