package hello.itemservice.repository.jpa;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@RequiredArgsConstructor
public class JpaItemRepositoryV2 implements ItemRepository {
	private final SpringDataJpaItemRepository springDataJpaItemRepository;

	@Override
	public Item save(Item item) {
		return springDataJpaItemRepository.save(item);
	}

	@Override
	public void update(Long itemId, ItemUpdateDto updateParam) {
		Item item = springDataJpaItemRepository.findById(itemId).orElseThrow();
		item.setItemName(updateParam.getItemName());
		item.setPrice(updateParam.getPrice());
		item.setQuantity(updateParam.getQuantity());
	}

	@Override
	public Optional<Item> findById(Long id) {
		return springDataJpaItemRepository.findById(id);
	}

	@Override
	public List<Item> findAll(ItemSearchCond cond) {
		String itemName = cond.getItemName();
		Integer maxPrice = cond.getMaxPrice();
		if (StringUtils.hasText(itemName) && maxPrice != null) {
			//return repository.findByItemNameLikeAndPriceLessThanEqual("%" + itemName +"%", maxPrice);
			return springDataJpaItemRepository.findItems("%" + itemName + "%", maxPrice);
		} else if (StringUtils.hasText(itemName)) {
			return springDataJpaItemRepository.findByItemNameLike("%" + itemName + "%");
		} else if (maxPrice != null) {
			return springDataJpaItemRepository.findByPriceLessThanEqual(maxPrice);
		} else {
			return springDataJpaItemRepository.findAll();
		}
	}
}
