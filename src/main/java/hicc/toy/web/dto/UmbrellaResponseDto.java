package hicc.toy.web.dto;

import hicc.toy.domain.member.Member;
import hicc.toy.domain.rental.RentalStatus;
import hicc.toy.domain.rental.Umbrella;
import lombok.Getter;

@Getter
public class UmbrellaResponseDto {
    private Long id;
    private RentalStatus rentalStatus;
    private Member member;

    public UmbrellaResponseDto(Umbrella umbrella) {
        this.id = umbrella.getId();;
        this.rentalStatus = umbrella.getRentalStatus();
        this.member = umbrella.getMember();
    }
}
