package com.codingbox.querydsl2;

import java.util.List;

import com.codingbox.querydsl2.domain.Member;
import static com.codingbox.querydsl2.domain.QMember.*;
import com.codingbox.querydsl2.domain.Team;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class QueryDSLMain {

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
			Member member5 = new Member(null, 100, teamB);
			Member member6 = new Member("member6", 100, teamB);
			Member member7 = new Member("member7", 100, teamB);

			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			em.persist(member4);
			em.persist(member5);
			em.persist(member6);
			em.persist(member7);

			em.flush();
			em.clear();

//			List<Member> members
//					= em.createQuery("select m from Member m", Member.class)
//					.getResultList();
//
//			for( Member member : members ) {
//				System.out.println("member : " + member);
//				System.out.println("-> member.team : " + member.getTeam());
//			}


//			// JPQL : member1 찾기
//
//			String jpqlString
//					= "select m from Member m where m.username = :username";
//
//			Member findByJpql = em.createQuery(jpqlString, Member.class)
//					.setParameter("username", "member1")
//					.getSingleResult();
//			System.out.println("findByJPQL : " + findByJpql.getUsername().equals("member1"));

//			// QMember의 이름을 부여한다. 별칭부여, 크게 중요하진 않음
////			QMember m = new QMember("m");
//			QMember m = QMember.member;
//			Member findByQueryDSL = queryFactory.select(member)
//					.from(member)
//					.where(member.username.eq("member1")
//							.and(member.age.eq(10))) // 파라미터 바인딩
//					.fetchOne();
//			System.out.println("findByQueryDSL : " + findByQueryDSL.getUsername().equals("member1"));

			// 초기화
//			//여러건
//			List<Member> fetch = queryFactory.select(member)
//					.from(member)
//					.fetch();
//			// 단건
//			Member findMember1 = queryFactory.selectFrom(member).fetchOne();

//			Long totalCount = queryFactory.select(member.count())
//					.from(member)
//					.fetchOne();
//			System.out.println(totalCount);
//
//
//			List<Member> result = queryFactory.selectFrom(member)
//							.where(member.age.eq(100))
//									.orderBy(member.age.desc(),member.username.asc())
//											.fetch();
//			Member resultMember5 = result.get(0);
//			Member resultMember6 = result.get(1);
//			Member memberNull = result.get(2);
//			System.out.println(resultMember5);
//			System.out.println(resultMember6);
//			System.out.println(memberNull);
//			List<Member> result = queryFactory.selectFrom(member)
//							.orderBy(member.username.desc())
//									.offset(1).limit(1).fetch();

			List<Tuple> result = queryFactory.select(member.count(),
							member.age.sum(),
							member.age.avg(),
							member.age.max(),
							member.age.min())
					.from(member)
					.fetch();
			Tuple tuple = result.get(0);
			System.out.println(tuple.get(member.count()));
			System.out.println(tuple.get(member.age.sum()));
			System.out.println(tuple.get(member.age.avg()));
			System.out.println(tuple.get(member.age.max()));
			System.out.println(tuple.get(member.age.min()));
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}


	}

}












