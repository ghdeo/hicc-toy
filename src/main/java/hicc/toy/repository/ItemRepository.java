package hicc.toy.repository;

import hicc.toy.domain.rental.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository<T extends Item> extends JpaRepository<T, Long> {
}
