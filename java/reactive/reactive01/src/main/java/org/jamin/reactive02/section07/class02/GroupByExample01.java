package org.jamin.reactive02.section07.class02;

import org.jamin.reactive02.common.Book;
import org.jamin.reactive02.common.SampleData;
import org.jamin.reactive02.utils.Logger;

import reactor.core.publisher.Flux;

/**
 * groupBy(keyMapper) 기본 개념 예제
 *  - emit되는 데이터를 keyMapper로 생성한 key를 기준으로 그룹화 한 GroupedFlux를 리턴한다.
 */
public class GroupByExample01 {
	public static void main(String[] args) {
		Flux
			.fromIterable(SampleData.books)
			.groupBy(Book::getAuthorName)
			.flatMap(groupedFlux ->
				groupedFlux
					.map(book -> book.getBookName() + "(" + book.getAuthorName() + ")")
					.collectList()
			)
			.subscribe(Logger::onNext);
	}
}
