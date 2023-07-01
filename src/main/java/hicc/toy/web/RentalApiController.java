package hicc.toy.web;

import hicc.toy.services.RentalService;
import hicc.toy.web.dto.LockerRequestDto;
import hicc.toy.web.dto.LockerResponseDto;
import hicc.toy.web.dto.UmbrellaRequestDto;
import hicc.toy.web.dto.UmbrellaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rental")
public class RentalApiController {
    private final RentalService rentalService;

    @PostMapping("/umbrella")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Long saveUmbrella(@RequestBody UmbrellaRequestDto requestDto) {
        return rentalService.saveUmbrella(requestDto);
    }

    @PostMapping("/locker")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Long saveLocker(@RequestBody LockerRequestDto requestDto) {
        return rentalService.saveLocker(requestDto);
    }

    @GetMapping("/umbrella")
    public List<UmbrellaResponseDto> findAllUmbrella() {
        return rentalService.findAllUmbrella();
    }

    @GetMapping("/locker")
    public List<LockerResponseDto> findAllLocker() {
        return rentalService.findAllLocker();
    }

    @PatchMapping("/umbrella/{id}")
    public Long updateUmbrella(@PathVariable Long id, @RequestBody UmbrellaRequestDto requestDto) {
        return rentalService.updateUmbrella(id, requestDto);
    }

    @PatchMapping("/locker/{id}")
    public Long updateLocker(@PathVariable Long id, @RequestBody LockerRequestDto requestDto) {
        return rentalService.updateLocker(id, requestDto);
    }

    @DeleteMapping("/umbrella/{id}")
    public Long deleteUmbrella(@PathVariable Long id) {
        return rentalService.deleteUmbrella(id);
    }

    @DeleteMapping("/locker/{id}")
    public Long deleteLocker(@PathVariable Long id) {
        return rentalService.deleteLocker(id);
    }
}
