package org.jamin.reactive01;

import reactor.core.publisher.Mono;

public class HelloReactor {
	public static void main(String[] args) {
		Mono.just("hello reactor")
			.subscribe(System.out::println);
	}
}
