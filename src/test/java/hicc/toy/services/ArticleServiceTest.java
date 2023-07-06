package hicc.toy.services;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.domain.aritcle.ArticleType;
import hicc.toy.domain.member.Member;
import hicc.toy.domain.member.MemberRole;
import hicc.toy.exception.CustomException;
import hicc.toy.repository.ArticleRepository;
import hicc.toy.repository.MemberRepository;
import hicc.toy.web.dto.ArticleRequestDto;
import hicc.toy.web.dto.ArticleResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        articleRepository.deleteAll();
        memberRepository.deleteAll();
    }
    @Test
    void save() {
        // given
        Member member = new Member(MemberRole.GENERAL);
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        ArticleRequestDto articleRequestDto = new ArticleRequestDto(ArticleType.FREE
                , "What a Beautiful Day"
                , "content"
                , LocalDateTime.now()
                , members.get(0));
        // when
        Long savedId = articleService.save(articleRequestDto);
        // then
        Article article = articleRepository.findById(savedId).get();
        Assertions.assertThat(savedId).isEqualTo(article.getId());

    }

    @Test
    void findByArticleTypeAndDeleteYn() {
        // given
        Member member = new Member(MemberRole.GENERAL);
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        ArticleRequestDto articleRequestDto = new ArticleRequestDto(
                ArticleType.FREE
                , "What a Beautiful Day"
                , "content"
                , LocalDateTime.now()
                , members.get(0));
        Long savedId = articleService.save(articleRequestDto);
        Pageable pageable = PageRequest.of(0, 15);
        // when
        Page<ArticleResponseDto> articles = articleService.findByArticleTypeAndDeleteYn(ArticleType.FREE,false, pageable);
        //then
        String getTitle = articles.getContent().get(0).getTitle();
        Assertions.assertThat(getTitle).isEqualTo("What a Beautiful Day");

    }

    @Test
    void searchArticles() {
        // given
        Member member = new Member(MemberRole.GENERAL);
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        ArticleRequestDto articleRequestDto = new ArticleRequestDto(
                ArticleType.FREE
                , "What a Beautiful Day"
                , "content"
                , LocalDateTime.now()
                , members.get(0));
        Long savedId = articleService.save(articleRequestDto);
        Pageable pageable = PageRequest.of(0, 15);
        // when
        Page<ArticleResponseDto> articles = articleService.searchArticles("What a Beautiful Day", ArticleType.FREE, false, pageable);
        // then
        ArticleResponseDto responseDto = articles.getContent().get(0);
        Assertions.assertThat(responseDto.getTitle()).isEqualTo("What a Beautiful Day");
    }

    @Test
    void findById() {
        // given
        Member member = new Member(MemberRole.GENERAL);
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        ArticleRequestDto articleRequestDto = new ArticleRequestDto(
                ArticleType.FREE
                , "What a Beautiful Day"
                , "content"
                , LocalDateTime.now()
                , members.get(0));
        Long savedId = articleService.save(articleRequestDto);
        // when
        ArticleResponseDto responseDto = articleService.findById(savedId);
        // then
        Assertions.assertThat(responseDto.getTitle()).isEqualTo("What a Beautiful Day");
    }

    @Test
    void update() {
        // given
        Member member = new Member(MemberRole.GENERAL);
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        ArticleRequestDto articleRequestDto = new ArticleRequestDto(
                ArticleType.FREE
                , "What a Beautiful Day"
                , "content"
                , LocalDateTime.now()
                , members.get(0));
        Long savedId = articleService.save(articleRequestDto);

        ArticleRequestDto updateRequestDto = new ArticleRequestDto(
                ArticleType.FREE
                , "What a Wonderful Day"
                , "content"
                , LocalDateTime.now()
                , members.get(0));

        // when
        articleService.update(savedId, updateRequestDto);

        // then
        Assertions.assertThat(articleService.findById(savedId).getTitle()).isEqualTo("What a Wonderful Day");
    }

    @Test
    void delete() {
        // given
        Member member = new Member(MemberRole.GENERAL);
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        ArticleRequestDto articleRequestDto = new ArticleRequestDto(
                ArticleType.FREE
                , "What a Beautiful Day"
                , "content"
                , LocalDateTime.now()
                , members.get(0));
        Long savedId = articleService.save(articleRequestDto);

        // when
        Long deletedId = articleService.delete(savedId);

        // then
        Assertions.assertThat(articleRepository
                .findById(deletedId)
                .get()
                .isDeleted())
                .isEqualTo(true);
    }
}