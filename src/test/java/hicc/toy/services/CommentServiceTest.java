package hicc.toy.services;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.domain.aritcle.ArticleType;
import hicc.toy.domain.comment.Comment;
import hicc.toy.domain.member.Member;
import hicc.toy.domain.member.MemberRole;
import hicc.toy.exception.CustomException;
import hicc.toy.repository.ArticleRepository;
import hicc.toy.repository.CommentRepository;
import hicc.toy.repository.MemberRepository;
import hicc.toy.web.dto.CommentRequestDto;
import hicc.toy.web.dto.CommentResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;

    Article createArticle() {
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
    @AfterEach
    void afterEach() {
        commentRepository.deleteAll();
        articleRepository.deleteAll();
        memberRepository.deleteAll();
    }
    @Test
    void save() {
        // given
        Article article = createArticle();
        CommentRequestDto comment = new CommentRequestDto(
                article.getId()
                , "comment1"
                , article.getMember()
                , article);
        // when
        commentService.save(comment);
        // then
        Assertions.assertThat(commentRepository
                .findByArticleId(article.getId())
                .get()
                .get(0)
                .getComment())
                .isEqualTo("comment1");
    }

    @Test
    void findByArticleId() {
        // given
        Article article = createArticle();
        Comment comment = Comment.builder().comment("comment1").article(article).member(article.getMember()).build();
        Comment savedComment = commentRepository.save(comment);
        // when
        List<CommentResponseDto> foundComments = commentService.findByArticleId(article.getId());
        // then
        Assertions.assertThat(foundComments.get(0).getComment()).isEqualTo("comment1");
    }

    @Test
    void update() {
        // given
        Article article = createArticle();
        Comment comment = Comment.builder().comment("comment1").article(article).member(article.getMember()).build();
        Comment savedComment = commentRepository.save(comment);
        CommentRequestDto updateComment = new CommentRequestDto(
                article.getId()
                , "modify"
                , article.getMember()
                , article);

        // when
        commentService.update(savedComment.getId(), updateComment);

        // then
        Assertions.assertThat(
                commentRepository
                .findById(savedComment.getId()).get().getComment())
                .isEqualTo("modify");
    }

    @Test
    void delete() {
        // given
        Article article = createArticle();
        Comment comment = Comment
                .builder()
                .comment("comment1")
                .article(article)
                .member(article.getMember())
                .build();
        Comment savedComment = commentRepository.save(comment);
        // when
        Long deletedId = commentService.delete(savedComment.getId());
        // then
        Assertions.assertThat(commentRepository.findById(deletedId).isEmpty()).isEqualTo(true);
    }
}