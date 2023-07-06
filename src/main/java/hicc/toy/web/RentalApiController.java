package hicc.toy.web;

import hicc.toy.domain.rental.ItemType;
import hicc.toy.services.RentalService;
import hicc.toy.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rental")
public class RentalApiController {
    private final RentalService rentalService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Long saveItem(@RequestBody ItemRequestDto requestDto) {
        return rentalService.saveItem(requestDto);
    }
    @GetMapping
    public List<ItemResponseDto> findAllItemByType(@RequestParam ItemType itemType) {
        return rentalService.findAllItemByType(itemType);
    }

    @PatchMapping("/{id}")
    public Long updateItem(@PathVariable Long id, @RequestBody ItemRequestDto requestDto) {
        return rentalService.updateItem(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long deleteItem(@PathVariable Long id) {
        return rentalService.deleteItem(id);
    }
}
