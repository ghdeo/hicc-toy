package hicc.toy.web.dto;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.domain.aritcle.ArticleType;
import hicc.toy.domain.member.Member;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ArticleResponseDto {
    private Long id;
    private ArticleType articleType;
    private String title;
    private String content;
    private LocalDateTime writtenDate;
    private List<CommentResponseDto> comments;
    private Member member;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.articleType = article.getArticleType();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.writtenDate = article.getWrittenDate();
        this.comments = article.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        this.member = article.getMember();
    }
}
