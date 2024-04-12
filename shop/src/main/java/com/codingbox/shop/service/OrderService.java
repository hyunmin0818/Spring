package com.codingbox.shop.service;

import com.codingbox.shop.domain.Item;
import com.codingbox.shop.domain.Member;
import com.codingbox.shop.domain.Order;
import com.codingbox.shop.domain.OrderItem;
import com.codingbox.shop.dto.OrderSearch;
import com.codingbox.shop.repository.ItemRepository;
import com.codingbox.shop.repository.MemberRepository;
import com.codingbox.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // jpa 영속성 컨텍스트 영역에 들어간다
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 주문 상품(원가, 할인가, 숫자)
        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);

        // add order
        Order order = Order.createOrder(member,orderItem);
        // set order
        orderRepository.save(order);
        return order.getId();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
