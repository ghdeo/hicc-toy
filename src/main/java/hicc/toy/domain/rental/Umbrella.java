package hicc.toy.domain.rental;

import hicc.toy.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Umbrella {

    @Id
    @GeneratedValue
    private Long id;
    private RentalStatus rentalStatus;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
