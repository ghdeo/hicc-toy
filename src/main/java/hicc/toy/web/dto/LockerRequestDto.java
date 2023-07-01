package hicc.toy.web.dto;

import hicc.toy.domain.member.Member;
import hicc.toy.domain.rental.Locker;
import hicc.toy.domain.rental.RentalStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LockerRequestDto {
    private RentalStatus rentalStatus;
    private Member member;

    @Builder
    public Locker toEntity() {
        return Locker.builder()
                .rentalStatus(rentalStatus)
                .member(member)
                .build();
    }
}
