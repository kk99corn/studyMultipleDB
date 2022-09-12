package com.example.multiple.domain.order.controller;

import com.example.multiple.domain.order.dto.OrderDto;
import com.example.multiple.domain.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/order/{seq}")
	public OrderDto getMember(@PathVariable(name = "seq") Integer seq) {
		return orderService.getOrder(seq);
	}

	@PostMapping("/order/{memberSeq}/{price}")
	public OrderDto saveOrder(@PathVariable(name = "price") Integer price,
							  @PathVariable(name = "memberSeq") Integer memberSeq) {
		return orderService.saveOrder(OrderDto.builder()
				.price(price)
				.memberSeq(memberSeq)
				.build());
	}
}
