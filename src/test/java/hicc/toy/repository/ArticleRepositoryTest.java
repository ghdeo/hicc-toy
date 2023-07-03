package hicc.toy.repository;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.domain.aritcle.ArticleType;
import hicc.toy.domain.member.Member;
import hicc.toy.domain.member.MemberRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    MemberRepository memberRepository;

    private Article createArticle() {
        Member member = new Member(MemberRole.GENERAL);
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();

        return new Article(
                ArticleType.FREE
                , "What a Beautiful Day"
                , "content"
                , LocalDateTime.now()
                , false
                , members.get(0)
        );
    }

    @AfterEach
    void afterEach() {
        articleRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void save() throws Exception {
        //given
        Article article = createArticle();
        //when
        Article save = articleRepository.save(article);
        //then
        Assertions.assertThat(save).isEqualTo(article);
    }


    @Test
    void findByArticleTypeAndDeleteYnTest() throws Exception {
        //given
        Article article = createArticle();
        Article save = articleRepository.save(article);

        ArticleType articleType = ArticleType.FREE;
        boolean isDeleted = false;
        Pageable pageable = PageRequest.of(0,10);

        //when
        Page<Article> result = articleRepository.findByArticleTypeAndIsDeleted(
                articleType
                , isDeleted
                , pageable
        );

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getContent()).hasSizeGreaterThan(0);
        Assertions.assertThat(result.getTotalElements()).isGreaterThan(0);
    }

    @Test
    void findByTitleContainingAndArticleTypeAndDeleteYnTest() throws Exception {
            //given
            Article article = createArticle();
            Article save = articleRepository.save(article);
            ArticleType articleType = ArticleType.FREE;
            boolean isDeleted = false;
            Pageable pageable = PageRequest.of(0,10);

            //when
            String keyWord = "Beautiful";
            Page<Article> result = articleRepository.findByTitleContainingAndArticleTypeAndIsDeleted(
                    keyWord
                    , articleType
                    , isDeleted
                    , pageable
            );

            //then
            Assertions.assertThat(result).isNotNull();
            Assertions.assertThat(result.getContent()).hasSizeGreaterThan(0);
            Assertions.assertThat(result.getTotalElements()).isGreaterThan(0);
    }

    @Test
    void findByIdTest() throws Exception {
        //given
        Article article = createArticle();
        Article savedArticle = articleRepository.save(article);
        //when
        Optional<Article> foundArticle = articleRepository.findById(savedArticle.getId());
        //then
        Assertions.assertThat(foundArticle).isNotEmpty();
        Assertions.assertThat(foundArticle.get().getTitle()).isEqualTo(article.getTitle());
    }
}