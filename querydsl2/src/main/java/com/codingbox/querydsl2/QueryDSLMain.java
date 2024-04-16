package com.codingbox.querydsl2;


import com.codingbox.querydsl2.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class QueryDSLMain {

	public static void main(String[] args) {
		System.out.println("ok================");

		EntityManagerFactory emf
			= Persistence.createEntityManagerFactory("hello");
		System.out.println("emf : " + emf);
		EntityManager em = emf.createEntityManager();		
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		System.out.println("em : " + em);
		System.out.println("tx : " + tx);
		try {
			Team teamA = new Team("Team A");
			em.persist(teamA);

			System.out.println("This_okok2");

			tx.commit();
			System.out.println("okokok");
		}catch (Exception e) {
			System.out.println("okokOKOK");
			e.printStackTrace();
			tx.rollback();
		} finally {
			System.out.println("OK");
			em.close();
			emf.close();
		}
		
		
	}

}












