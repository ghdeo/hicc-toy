package hicc.toy.domain.aritcle;

import hicc.toy.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@Entity
@Getter
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
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Article(ArticleType articleType, String title, String content, LocalDateTime writtenDate, Member member) {
        this.articleType = articleType;
        this.title = title;
        this.content = content;
        this.writtenDate = writtenDate;
        this.member = member;
    }

    public void update(ArticleType articleType, String title, String content) {
        this.articleType = articleType;
        this.title = title;
        this.content = content;
        this.writtenDate = LocalDateTime.now();
    }
}
