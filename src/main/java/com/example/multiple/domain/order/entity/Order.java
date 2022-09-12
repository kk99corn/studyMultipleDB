package com.example.multiple.domain.order.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seq;
	private Integer price;
	private Integer memberSeq;

	public Order(Integer price, Integer memberSeq) {
		this.price = price;
		this.memberSeq = memberSeq;
	}
}
