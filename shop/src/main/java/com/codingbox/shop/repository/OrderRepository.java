package com.codingbox.shop.repository;

import com.codingbox.shop.domain.Order;
import com.codingbox.shop.domain.QMember;
import com.codingbox.shop.domain.QOrder;
import com.codingbox.shop.dto.OrderSearch;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    // 검색, 전체 조회, QueryDSL - 실무에서 많이 사용
    public List<Order> findAll(OrderSearch orderSearch) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QOrder order = QOrder.order;
        QMember member = QMember.member;


        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

}
