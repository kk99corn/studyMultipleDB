package com.example.multiple.domain.order.repository;

import com.example.multiple.domain.order.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional("orderTransactionManager")
class OrderRepositoryTest {

	@Autowired
	OrderRepository orderRepository;

	@Test
	void test() {
		// Given
		Integer price = 50000;
		Integer memberSeq = 1;
		Order saveOrder = orderRepository.save(new Order(price, memberSeq));

		// When
		Optional<Order> findOrder = orderRepository.findById(saveOrder.getSeq());

		// Then
		if (findOrder.isPresent()) {
			Order order = findOrder.get();

			assertThat(order.getSeq()).isEqualTo(saveOrder.getSeq());
			assertThat(order.getPrice()).isEqualTo(price);
		}
	}

}