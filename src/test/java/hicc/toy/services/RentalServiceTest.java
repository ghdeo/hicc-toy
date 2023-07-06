package hicc.toy.services;

import hicc.toy.domain.member.Member;
import hicc.toy.domain.member.MemberRole;
import hicc.toy.domain.rental.Item;
import hicc.toy.domain.rental.ItemType;
import hicc.toy.domain.rental.RentalStatus;
import hicc.toy.exception.CustomException;
import hicc.toy.repository.ItemRepository;
import hicc.toy.repository.MemberRepository;
import hicc.toy.web.dto.ItemRequestDto;
import hicc.toy.web.dto.ItemResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RentalServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RentalService rentalService;
    @Autowired
    ItemRepository itemRepository;

    @AfterEach
    void afterEach() {
        itemRepository.deleteAll();
        memberRepository.deleteAll();
    }
    @Test
    void saveItem() {
        // given
        Member member = new Member(MemberRole.GENERAL);
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        ItemRequestDto requestDto = new ItemRequestDto(
                ItemType.UMBRELLA,
                RentalStatus.AVAILABLE,
                members.get(0)
        );
        // when
        Long savedItemId = rentalService.saveItem(requestDto);
        // then
        Assertions.assertThat(
                itemRepository
                        .findById(savedItemId)
                        .get()
                        .getItemType())
                .isEqualTo(ItemType.UMBRELLA);
    }

    @Test
    void findAllItemByType() {
        // given
        Item item1 = Item.builder().itemType(ItemType.UMBRELLA).rentalStatus(RentalStatus.AVAILABLE).build();
        Item item2 = Item.builder().itemType(ItemType.UMBRELLA).rentalStatus(RentalStatus.AVAILABLE).build();
        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<ItemResponseDto> items = rentalService.findAllItemByType(ItemType.UMBRELLA);

        // then
        Assertions.assertThat(items.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void updateItem() {
        // given
        Member member = new Member(MemberRole.GENERAL);
        memberRepository.save(member);
        List<Member> members = memberRepository.findAll();
        ItemRequestDto requestDto = new ItemRequestDto(
                ItemType.UMBRELLA,
                RentalStatus.AVAILABLE,
                members.get(0)
        );
        Long savedItemId = rentalService.saveItem(requestDto);
        ItemRequestDto updateDto = new ItemRequestDto(
                ItemType.UMBRELLA,
                RentalStatus.RENTED,
                members.get(0)
        );

        // when
        Long updatedItemId = rentalService.updateItem(savedItemId, updateDto);

        // then
        Assertions.assertThat(
                itemRepository
                        .findById(updatedItemId)
                        .get().getRentalStatus())
                .isEqualTo(RentalStatus.RENTED);
    }

    @Test
    void deleteItem() {
        // given
        Item item = Item.builder().itemType(ItemType.UMBRELLA).rentalStatus(RentalStatus.AVAILABLE).build();
        Item savedItem = itemRepository.save(item);

        // when
        rentalService.deleteItem(savedItem.getId());
        // then
        Optional<Item> byId = itemRepository.findById(savedItem.getId());
        Assertions.assertThat(byId.isEmpty()).isEqualTo(true);

    }
}