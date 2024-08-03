package hello.itemservice.repository.v2;

import static hello.itemservice.domain.QItem.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;

@Repository
public class ItemQueryRepositoryV2 {
	private final JPAQueryFactory jpaQueryFactory;

	public ItemQueryRepositoryV2(EntityManager em) {
		this.jpaQueryFactory = new JPAQueryFactory(em);
	}

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

