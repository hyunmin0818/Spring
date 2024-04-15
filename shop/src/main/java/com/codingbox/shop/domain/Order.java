package com.codingbox.shop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
	@Id @GeneratedValue
	@Column(name = "order_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	// 주문시간
	private LocalDateTime orderDate;
	
	// 주문상태(ORDER, CANCEL)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems
		= new ArrayList<>();
	
	//////////////////// 연관관계 메서드 /////////////////////////
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	// ======== 비지니스 로직 ============
	public static Order createOrder(Member member, OrderItem... orderItems) {
		Order order = new Order();
		order.setMember(member);
		for( OrderItem orderItem : orderItems ) {
			order.addOrderItem(orderItem);
		}
		order.setStatus(OrderStatus.ORDER);
		order.setOrderDate(LocalDateTime.now());
		
		return order;
	}

	// 주문 취소
	public void cancle() {
		this.setStatus(OrderStatus.CANCEL);
		for( OrderItem orderItem : orderItems ) {
			orderItem.cancel();
		}
	}
	
	
	
	
	
	
	
}












