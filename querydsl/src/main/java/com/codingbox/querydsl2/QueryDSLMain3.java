package com.codingbox.querydsl2;


import com.codingbox.querydsl2.domain.Member;
import com.codingbox.querydsl2.domain.Team;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.awt.*;
import java.util.List;

import static com.codingbox.querydsl2.domain.QMember.member;

public class QueryDSLMain3 {

	public static void main(String[] args) {
		EntityManagerFactory emf
			= Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();		
		EntityTransaction tx = em.getTransaction();
		// queryDSL
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		tx.begin();
		
		try {
			Team teamA = new Team("teamA");
			Team teamB = new Team("teamB");
			em.persist(teamA);
			em.persist(teamB);
			
			Member member1 = new Member("member1", 10, teamA);
			Member member2 = new Member("member2", 20, teamA);
			Member member3 = new Member("member3", 30, teamB);
			Member member4 = new Member("member4", 40, teamB);
			Member member5 = new Member(null, 50, teamB);
			Member member6 = new Member("member6", 60, teamB);
			Member member7 = new Member("member7", 70, teamB);
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);
			em.persist(member5);
			em.persist(member6);
			em.persist(member7);
			
			// 초기화
			em.flush();
			em.clear();

			List<Member> result = queryFactory.selectFrom(member)
					.where(member.age.eq(100))
					.orderBy(member.age.desc(), member.username.asc().nullsLast())
					.fetch();

			Member resultMember5 = result.get(0);
			Member resultMember6 = result.get(1);
			Member memberNull = result.get(2);

			System.out.println(resultMember5);
			System.out.println(resultMember6);
			System.out.println(memberNull);

			tx.commit();
		}catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
		
	}

}
