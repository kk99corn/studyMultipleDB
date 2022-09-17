package com.example.multiple.domain.order.service;

import com.example.multiple.domain.order.dto.OrderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional("orderTransactionManager")
class OrderServiceTest {

	@Autowired
	OrderService orderService;

	@Test
	void test() {
		// Given
		Integer price = 50000;
		Integer memberSeq = 1;
		OrderDto orderDto = OrderDto.builder()
				.price(price)
				.memberSeq(memberSeq)
				.build();

		// When
		orderDto = orderService.saveOrder(orderDto);

		// Then
		assertThat(orderDto.getSeq()).isGreaterThan(0);
		assertThat(orderDto.getPrice()).isEqualTo(price);
		assertThat(orderDto.getMemberSeq()).isEqualTo(memberSeq);
	}
}