package hicc.toy.web.dto;

import hicc.toy.domain.member.Member;
import hicc.toy.domain.rental.RentalStatus;
import hicc.toy.domain.rental.Umbrella;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UmbrellaRequestDto {
    private RentalStatus rentalStatus;
    private Member member;

    @Builder
    public Umbrella toEntity() {
        return Umbrella.builder()
                .rentalStatus(rentalStatus)
                .member(member)
                .build();
    }
}
