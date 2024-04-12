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

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> order_items = new ArrayList<OrderItem>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    // business logic
    public static Order createOrder(Member member, OrderItem... orderItems /* test/Main03 */) {
        Order order = new Order();
        order.setMember(member);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setOrder_status(OrderStatus.ORDER);
        order.setOrder_date(LocalDateTime.now());
        return order;
    }

    // --------------------------------------------------------연관관계 메서드
    public void setMember(){
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        order_items.add(orderItem);
        orderItem.setOrder(this);
    }

}
