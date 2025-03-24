package org.jamin.reactive02.section07.class02;

import org.jamin.reactive02.common.Book;
import org.jamin.reactive02.common.SampleData;
import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * groupBy 활용 예제
 *  -저자별로 그룹화 한 도서가 모두 판매 되었을 때의 총 인세 수익을 계산한다.
 */
public class GroupByExample03 {
	public static void main(String[] args) {
		Flux
			.fromIterable(SampleData.books)
			.groupBy(Book::getAuthorName)
			.flatMap(groupedFlux ->
				Mono
					.just(groupedFlux.key())
					.zipWith(
						groupedFlux
							.map(book -> (int)(book.getPrice() * book.getStockQuantity() * 0.1))
							.reduce(Integer::sum),
						(authorName, sumRoyalty) -> authorName + "'s royalty: " + sumRoyalty)
			)
			.subscribe(Logger::onNext);
	}
}
