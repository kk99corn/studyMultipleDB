package com.example.multiple.domain.order.service;

import com.example.multiple.domain.order.dto.OrderDto;
import com.example.multiple.domain.order.entity.Order;
import com.example.multiple.domain.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public OrderDto saveOrder(OrderDto orderDto) {
		Order savedOrder = orderRepository.save(new Order(orderDto.getPrice(), orderDto.getMemberSeq()));
		return OrderDto.builder()
				.seq(savedOrder.getSeq())
				.price(savedOrder.getPrice())
				.memberSeq(savedOrder.getMemberSeq())
				.build();
	}

	public OrderDto getOrder(Integer seq) {
		OrderDto orderDto = null;
		Optional<Order> findOrder = orderRepository.findById(seq);
		if (findOrder.isPresent()) {
			Order order = findOrder.get();
			orderDto = OrderDto.builder()
					.seq(order.getSeq())
					.price(order.getPrice())
					.memberSeq(order.getMemberSeq())
					.build();
		}
		return orderDto;
	}
}
