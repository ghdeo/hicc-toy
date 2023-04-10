package hicc.toy.web.dto;

import hicc.toy.domain.comment.Comment;
import hicc.toy.domain.member.Member;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id;
    private String comment;
    private Long memberId;
    private Long articleId;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.memberId = comment.getMember().getId();
        this.articleId = comment.getArticle().getId();
    }
}
