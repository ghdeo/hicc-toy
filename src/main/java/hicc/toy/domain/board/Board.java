package hicc.toy.domain.board;

import hicc.toy.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    private BoardType boardType;
    private String title;
    private String content;
    private Date writtenDate;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
