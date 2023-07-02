package hicc.toy.repository;

import hicc.toy.domain.rental.Item;
import hicc.toy.domain.rental.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemType(ItemType itemType);
}
