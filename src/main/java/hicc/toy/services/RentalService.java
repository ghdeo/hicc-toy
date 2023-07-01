package hicc.toy.services;

import hicc.toy.domain.rental.Locker;
import hicc.toy.domain.rental.Umbrella;
import hicc.toy.exception.CustomException;
import hicc.toy.exception.ErrorCode;
import hicc.toy.repository.LockerRepository;
import hicc.toy.repository.UmbrellaRepository;
import hicc.toy.web.dto.LockerRequestDto;
import hicc.toy.web.dto.LockerResponseDto;
import hicc.toy.web.dto.UmbrellaRequestDto;
import hicc.toy.web.dto.UmbrellaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final UmbrellaRepository umbrellaRepository;
    private final LockerRepository lockerRepository;

    @Transactional
    public Long saveUmbrella(final UmbrellaRequestDto requestDto) {
        return umbrellaRepository
                .save(requestDto.toEntity())
                .getId();
    }

    @Transactional
    public Long saveLocker(final LockerRequestDto requestDto) {
        return lockerRepository
                .save(requestDto.toEntity())
                .getId();
    }

    @Transactional(readOnly = true)
    public List<UmbrellaResponseDto> findAllUmbrella() {
        List<Umbrella> list = umbrellaRepository.findAll();
        return list.stream().map(UmbrellaResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LockerResponseDto> findAllLocker() {
        List<Locker> list = lockerRepository.findAll();
        return list.stream().map(LockerResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long updateUmbrella(Long id, UmbrellaRequestDto requestDto) {
        Umbrella entity = umbrellaRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.UMBRELLA_NOT_FOUND));
        entity.update(requestDto.getRentalStatus());
        return entity.getId();
    }

    @Transactional
    public Long updateLocker(Long id, LockerRequestDto requestDto) {
        Locker entity = lockerRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.LOCKER_NOT_FOUND));
        entity.update(requestDto.getRentalStatus());
        return entity.getId();
    }

    @Transactional
    public Long deleteUmbrella(Long id) {
        Umbrella entity = umbrellaRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.UMBRELLA_NOT_FOUND));
        umbrellaRepository.delete(entity);
        return id;
    }

    @Transactional
    public Long deleteLocker(Long id) {
        Locker entity = lockerRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.LOCKER_NOT_FOUND));
        lockerRepository.delete(entity);
        return id;
    }
}
