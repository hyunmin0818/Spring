package com.codingbox.test2;

import com.codingbox.test2.member.Address;
import com.codingbox.test2.member.Member;
import com.codingbox.test2.order.Order;
import com.codingbox.test2.order.OrderStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class TestMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Member 생성
            Member member = new Member();
            member.setName("김케장");
            Address address = new Address("인천", "후드", "123");
            member.setAddress(address);
            em.persist(member);

            // Order 데이터 생성 및 Member와  연관 지정 후 저장
            for (int i = 0; i < 10; i++) {
                Order order = new Order();
                order.setOrderDate(LocalDateTime.now());
                order.setStatus(OrderStatus.ORDER);
                order.setMember(member);
                em.persist(order);
            }

            tx.commit();

            // 콘솔에 출력
            System.out.println("Member 정보: " + member);
            System.out.println("주문 목록: ");
            for (Order order : member.getOrders()) {
                System.out.println(order);
            }
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
