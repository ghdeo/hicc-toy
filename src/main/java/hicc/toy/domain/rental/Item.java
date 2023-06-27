package hicc.toy.domain.rental;

import hicc.toy.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private RentalStatus rentalStatus;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
