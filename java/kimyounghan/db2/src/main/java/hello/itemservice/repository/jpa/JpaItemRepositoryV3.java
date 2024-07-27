package hello.itemservice.repository.jpa;

import static hello.itemservice.domain.QItem.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional
public class JpaItemRepositoryV3 implements ItemRepository {
	private final EntityManager em;
	private final JPAQueryFactory jpaQueryFactory;

	public JpaItemRepositoryV3(EntityManager em) {
		this.em = em;
		this.jpaQueryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Item save(Item item) {
		em.persist(item);
		return item;
	}

	@Override
	public void update(Long itemId, ItemUpdateDto updateParam) {
		Item item = em.find(Item.class, itemId);
		item.setItemName(updateParam.getItemName());
		item.setPrice(updateParam.getPrice());
		item.setQuantity(updateParam.getQuantity());
	}

	@Override
	public Optional<Item> findById(Long id) {
		Item item = em.find(Item.class, id);
		return Optional.ofNullable(item);
	}

	@Override
	public List<Item> findAll(ItemSearchCond cond) {
		return jpaQueryFactory.select(item)
			.from(item)
			.where(itemNameLike(cond.getItemName()), maxPriceLoe(cond.getMaxPrice()))
			.fetch();
	}

	private BooleanExpression itemNameLike(String itemName) {
		return StringUtils.hasText(itemName) ? item.itemName.like("%" + itemName + "%") : null;
	}

	private BooleanExpression maxPriceLoe(Integer maxPrice) {
		return maxPrice == null ? null : item.price.loe(maxPrice);
	}
}
