package hello.itemservice.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import hello.itemservice.config.V2Config;
import hello.itemservice.domain.Item;

@SpringBootTest
@Import(V2Config.class)
class ItemServiceV2Test {
	@Autowired
	ItemServiceV2 service;


	@Test
	void testGetItems() {
		assertNotNull(service.save(new Item("zzzz", 2222, 2222)));
	}
}