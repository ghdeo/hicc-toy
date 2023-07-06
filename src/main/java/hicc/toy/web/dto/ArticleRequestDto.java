package hicc.toy.web.dto;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.domain.aritcle.ArticleType;
import hicc.toy.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ArticleRequestDto {
    private ArticleType articleType;
    private String title;
    private String content;
    private LocalDateTime writtenDate;
    private Member member;

    @Builder
    public Article toEntity() {
        return Article.builder()
                .articleType(articleType)
                .title(title)
                .content(content)
                .writtenDate(writtenDate)
                .member(member)
                .build();
    }
}
