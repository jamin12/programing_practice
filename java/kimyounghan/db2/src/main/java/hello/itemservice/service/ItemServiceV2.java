package hello.itemservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import hello.itemservice.repository.v2.ItemQueryRepositoryV2;
import hello.itemservice.repository.v2.ItemRepositoryV2;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceV2 implements ItemService {

	private final ItemRepositoryV2 itemRepositoryV2;
	private final ItemQueryRepositoryV2 itemQueryRepositoryV2;

	@Override
	public Item save(Item item) {
		Item save = itemRepositoryV2.save(item);
		update(10L, new ItemUpdateDto("qwer", 1111, 1112));
		return save;
	}

	@Override
	public void update(Long itemId, ItemUpdateDto updateParam) {
		Item item = itemRepositoryV2.findById(itemId).orElseThrow();
		item.setItemName(updateParam.getItemName());
		item.setPrice(updateParam.getPrice());
		item.setQuantity(updateParam.getQuantity());
		throw new IllegalStateException("qwer");
	}

	@Override
	public Optional<Item> findById(Long id) {
		return itemRepositoryV2.findById(id);
	}

	@Override
	public List<Item> findItems(ItemSearchCond cond) {
		return itemQueryRepositoryV2.findAll(cond);
	}
}
