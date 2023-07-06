package hicc.toy.web.dto;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.domain.comment.Comment;
import hicc.toy.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private Long id;
    private String comment;
    private Member member;
    private Article article;

    @Builder
    public Comment toEntity() {
        return Comment.builder()
                .id(id)
                .comment(comment)
                .member(member)
                .article(article)
                .build();
    }
}
