package hicc.toy.domain.aritcle;

import hicc.toy.domain.comment.Comment;
import hicc.toy.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@Entity
@Getter
@DynamicInsert
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    private ArticleType articleType;
    @Column(length = 60, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private LocalDateTime writtenDate;
    @Column
    private boolean isDeleted;
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Article(ArticleType articleType, String title, String content, LocalDateTime writtenDate,boolean isDeleted, Member member) {
        this.articleType = articleType;
        this.title = title;
        this.content = content;
        this.writtenDate = writtenDate;
        this.isDeleted = isDeleted;
        this.member = member;
    }

    public void update(ArticleType articleType, String title, String content) {
        this.articleType = articleType;
        this.title = title;
        this.content = content;
        this.writtenDate = LocalDateTime.now();
    }

    public void delete() {
        this.isDeleted = true;
    }
}
