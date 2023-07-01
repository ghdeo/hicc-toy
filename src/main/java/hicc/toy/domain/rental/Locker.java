package hicc.toy.domain.rental;

import hicc.toy.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Locker {
    @Id
    @GeneratedValue
    private Long id;
    private RentalStatus rentalStatus;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
