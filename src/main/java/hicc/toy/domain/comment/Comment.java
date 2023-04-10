package hicc.toy.domain.comment;

import hicc.toy.domain.aritcle.Article;
import hicc.toy.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Comment(Long id, String comment, Article article, Member member) {
        this.id = id;
        this.comment = comment;
        this.article = article;
        this.member = member;
    }

    public void update(String comment) {
        this.comment = comment;
    }
}
