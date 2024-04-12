package com.codingbox.shop.dto;

import com.codingbox.shop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

// 검색 조건
@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus status;

}
