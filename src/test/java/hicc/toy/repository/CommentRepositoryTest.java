package hicc.toy.repository;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.domain.aritcle.ArticleType;
import hicc.toy.domain.comment.Comment;
import hicc.toy.domain.member.Member;
import hicc.toy.domain.member.MemberRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CommentRepository commentRepository;

    @AfterEach
    void afterEach() {
        commentRepository.deleteAll();
        articleRepository.deleteAll();
        memberRepository.deleteAll();
    }

    private Article createArticle() {
        Member member = new Member(MemberRole.GENERAL);
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        Article article = new Article(
                ArticleType.FREE
                , "What a Beautiful Day"
                , "content"
                , LocalDateTime.now()
                , false
                , members.get(0)
        );
        Article save = articleRepository.save(article);
        return save;
    }

    @Test
    void save() throws Exception {
        //given
        Article article = createArticle();
        Comment comment = Comment.builder()
                .comment("hi")
                .article(article)
                .member(article.getMember())
                .build();
        //when
        Comment save = commentRepository.save(comment);
        //then
        Assertions.assertThat(save.getComment()).isEqualTo("hi");
    }

    @Test
    void findByArticleId() throws Exception {
        //given
        Article article = createArticle();
        Comment comment = Comment.builder()
                .comment("hi")
                .article(article)
                .member(article.getMember())
                .build();
        Comment save = commentRepository.save(comment);
        //when
        Optional<List<Comment>> byArticleId = commentRepository.findByArticleId(article.getId());
        //then
        Assertions.assertThat(byArticleId.get().get(0).getComment()).isEqualTo("hi");
    }
}
