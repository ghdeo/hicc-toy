package hicc.toy.web.dto;

import hicc.toy.domain.member.Member;
import hicc.toy.domain.rental.Item;
import hicc.toy.domain.rental.ItemType;
import hicc.toy.domain.rental.RentalStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemRequestDto {
    private ItemType itemType;
    private RentalStatus rentalStatus;
    private Member member;

    @Builder
    public Item toEntity() {
        return Item.builder()
                .itemType(itemType)
                .rentalStatus(rentalStatus)
                .member(member)
                .build();
    }
}
