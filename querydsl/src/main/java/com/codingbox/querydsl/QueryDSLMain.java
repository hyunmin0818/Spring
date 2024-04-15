package com.codingbox.querydsl;

import java.util.List;

import com.codingbox.querydsl.domain.Member;
import com.codingbox.querydsl.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class QueryDSLMain {

	public static void main(String[] args) {
		System.out.println("ok");

		EntityManagerFactory emf
			= Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();		
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			System.out.println("This_okok");
			Team teamA = new Team("Team A");
			Team teamB = new Team("Team B");
			em.persist(teamA);
			em.persist(teamB);

			System.out.println("This_okok2");
			Member member1 = new Member("member1", 10, teamA);
			Member member2 = new Member("member2", 20, teamA);
			Member member3 = new Member("member3", 30, teamB);
			Member member4 = new Member("member4", 40, teamB);
			System.out.println("This_okok3");
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);

			System.out.println("This_okok4");
			// 초기화
			em.flush();
			em.clear();

			System.out.println("okok");

			tx.commit();
			System.out.println("okokok");
		}catch (Exception e) {
			System.out.println("okokOKOK");
			//e.printStackTrace();
			tx.rollback();
		} finally {
			System.out.println("OK");
			em.close();
			emf.close();
		}
		
		
	}

}












