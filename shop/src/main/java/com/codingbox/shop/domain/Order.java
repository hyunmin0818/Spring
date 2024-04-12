package com.codingbox.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name ="orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // 주문 시간
    private LocalDateTime order_date;

    // 주문상태(ORDER, CANCEL)
    @Enumerated(EnumType.STRING)
    private OrderStatus order_status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> order_items = new ArrayList<OrderItem>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // --------------------------------------------------------연관관계 메서드
    public void setMember(){
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        order_items.add(orderItem);
        orderItem.setOrder(this);
    }

}
