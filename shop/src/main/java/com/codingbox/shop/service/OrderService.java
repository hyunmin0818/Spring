package com.codingbox.shop.service;

import com.codingbox.shop.domain.Item;
import com.codingbox.shop.domain.Member;
import com.codingbox.shop.domain.OrderItem;
import com.codingbox.shop.repository.ItemRepository;
import com.codingbox.shop.repository.MemberRepository;
import com.codingbox.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void order(Long memberId, Long itemId, int count) {
        // jpa 영속성 컨텍스트 영역에 들어간다
        Member member = MemberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 주문 상품
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
    }

}
