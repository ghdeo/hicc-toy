package hicc.toy.repository;

import hicc.toy.domain.member.Member;
import hicc.toy.domain.member.MemberRole;
import hicc.toy.domain.rental.Item;
import hicc.toy.domain.rental.ItemType;
import hicc.toy.domain.rental.RentalStatus;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberRepository memberRepository;
    @AfterEach
    void afterEach() {
        itemRepository.deleteAll();
        memberRepository.deleteAll();
    }
    @Test
    void save() throws Exception {
        //given
        Member member = Member.builder().memberRole(MemberRole.GENERAL).build();
        Member savedMember = memberRepository.save(member);
        Item item = Item.builder().itemType(ItemType.UMBRELLA).rentalStatus(RentalStatus.RENTED).member(savedMember).build();
        //when
        Item savedItem = itemRepository.save(item);
        //then
        Assertions.assertThat(savedItem.getMember().getId()).isEqualTo(savedMember.getId());
    }

    @Test
    @Transactional
    void update() throws Exception {
        //given
        Member member = Member.builder().memberRole(MemberRole.GENERAL).build();
        Member savedMember = memberRepository.save(member);
        Item item = Item.builder()
                .itemType(ItemType.UMBRELLA)
                .rentalStatus(RentalStatus.RENTED)
                .member(savedMember)
                .build();
        Item savedItem = itemRepository.save(item);
        //when
        savedItem.update(RentalStatus.AVAILABLE);
        Item updateItem = itemRepository.findById(savedItem.getId()).get();
        //then
        Assertions.assertThat(updateItem.getRentalStatus()).isEqualTo(RentalStatus.AVAILABLE);
    }

}