package hello.itemservice.repository.v2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.itemservice.domain.Item;

@Repository
public interface ItemRepositoryV2 extends JpaRepository<Item, Long> {
}
