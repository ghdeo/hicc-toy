package hicc.toy.web.dto;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.domain.comment.Comment;
import hicc.toy.domain.member.Member;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id;
    private String comment;
    private Member member;
    private Article article;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.member = comment.getMember();
        this.article = comment.getArticle();
    }
}
