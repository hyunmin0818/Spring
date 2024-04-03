package com.codingbox.jpa;

import com.codingbox.jpa.entity.Member;
import jakarta.persistence.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JpaMain {
    public static void main(String[] args) {
        Properties props = new Properties();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            Member member = new Member();


            // 회원 조회
            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id: " + findMember.getId());
//            System.out.println("findMember.name: " + findMember.getName());

            // 회원수정
            findMember.setName("HELLO");

            // 회원 삭제
            em.remove(findMember);

            // 추가
//            member.setId(1L);
//            member.setName("UserA");

//            em.persist(findMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }
}
