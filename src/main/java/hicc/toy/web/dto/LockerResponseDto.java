package hicc.toy.web.dto;

import hicc.toy.domain.member.Member;
import hicc.toy.domain.rental.Locker;
import hicc.toy.domain.rental.RentalStatus;
import lombok.Getter;

@Getter
public class LockerResponseDto {
    private Long id;
    private RentalStatus rentalStatus;
    private Member member;

    public LockerResponseDto(Locker locker) {
        this.id = locker.getId();
        this.rentalStatus = locker.getRentalStatus();
        this.member = locker.getMember();
    }
}
