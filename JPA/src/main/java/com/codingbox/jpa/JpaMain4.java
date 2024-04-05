package com.codingbox.jpa;

import com.codingbox.jpa.entity.Member;
import com.codingbox.jpa.entity.Member3;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain4 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            System.out.println("===================tx===================== :" + tx);

            //Member3 member = new Member3();
//            member.setId(Long.parseLong("ID_A"));
//            member.setUsername("HelloJPA");
//
//            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            System.out.println("===================test=====================");
            em.close();
            emf.close();
        }

    }

}