package com.codingbox.jpql.domain.repository;

import com.codingbox.jpql.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}