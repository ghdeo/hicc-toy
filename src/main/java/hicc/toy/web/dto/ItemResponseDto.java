package hicc.toy.web.dto;

import hicc.toy.domain.member.Member;
import hicc.toy.domain.rental.Item;
import hicc.toy.domain.rental.ItemType;
import hicc.toy.domain.rental.RentalStatus;
import lombok.Getter;

@Getter
public class ItemResponseDto {
    private Long id;
    private ItemType itemType;
    private RentalStatus rentalStatus;
    private Member member;

    public ItemResponseDto(Item item) {
        this.id = item.getId();
        this.itemType = item.getItemType();
        this.rentalStatus = item.getRentalStatus();
        this.member = item.getMember();
    }
}
