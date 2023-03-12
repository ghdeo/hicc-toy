package hicc.toy.web.dto;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.domain.aritcle.ArticleType;
import hicc.toy.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class ArticleSaveRequestDto {
    private ArticleType articleType;
    private String title;
    private String content;
    Date writtenDate;
    private Member member;

    public ArticleSaveRequestDto(ArticleType articleType, String title, String content, Date writtenDate, Member member) {
        this.articleType = articleType;
        this.title = title;
        this.content = content;
        this.writtenDate = writtenDate;
        this.member = member;
    }

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
