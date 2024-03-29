package hicc.toy.domain.rental;

import hicc.toy.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private ItemType itemType;
    private RentalStatus rentalStatus;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void update(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
}
