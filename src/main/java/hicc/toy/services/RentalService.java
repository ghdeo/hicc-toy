package hicc.toy.services;

import hicc.toy.domain.rental.Item;
import hicc.toy.domain.rental.ItemType;
import hicc.toy.exception.CustomException;
import hicc.toy.exception.ErrorCode;
import hicc.toy.repository.ItemRepository;
import hicc.toy.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(final ItemRequestDto requestDto) {
        return itemRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<ItemResponseDto> findAllItemByType(final ItemType itemType) {
        List<Item> list = itemRepository.findByItemType(itemType);
        return list.stream().map(ItemResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long updateItem(Long id, ItemRequestDto requestDto) {
        Item entity = itemRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.ITEM_NOT_FOUND));
        entity.update(requestDto.getRentalStatus());
        return entity.getId();
    }

    @Transactional
    public Long deleteItem(Long id) {
        Item entity = itemRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.ITEM_NOT_FOUND));
        itemRepository.delete(entity);
        return id;
    }
}
