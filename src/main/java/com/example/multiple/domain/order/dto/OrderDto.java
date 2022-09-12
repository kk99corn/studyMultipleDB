package com.example.multiple.domain.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDto {

	private Integer seq;
	private Integer price;
	private Integer memberSeq;
}
