package com.codingbox.querydsl2;

import java.util.List;

import com.codingbox.querydsl2.domain.Member;

import static com.codingbox.querydsl2.domain.QMember.*;
import static com.codingbox.querydsl2.domain.QTeam.team;

import com.codingbox.querydsl2.domain.MemberDto;
import com.codingbox.querydsl2.domain.QMember;
import com.codingbox.querydsl2.domain.Team;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QTuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class QueryDSLMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
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

//			List<Tuple> result = queryFactory.select(member.count(),
//							member.age.sum(),
//							member.age.avg(),
//							member.age.max(),
//							member.age.min())
//					.from(member)
//					.fetch();
//			Tuple tuple = result.get(0);
//			System.out.println(tuple.get(member.count()));
//			System.out.println(tuple.get(member.age.sum()));
//			System.out.println(tuple.get(member.age.avg()));
//			System.out.println(tuple.get(member.age.max()));
//			System.out.println(tuple.get(member.age.min()));

            // 팀 이름과, 각 팀의 평균 연령 구하기
//			List<Tuple> result = queryFactory.select(team.name, member.age.avg())
//					.from(member)
//					.join(member.team, team)
//					.groupBy(team.name)
//					.having(member.age.avg().gt(10))
//					.fetch();
//			Tuple resultTeamA = result.get(0);
//			Tuple resultTeamB = result.get(1);
//			System.out.println("resultTeamA : " + resultTeamA);
//			System.out.println("resultTeamB : " + resultTeamB);

            // member에 있는 team과 team을 join
//            List<Member> result = queryFactory.selectFrom(member).leftJoin(member.team, team).where(team.name.eq("teamA")).fetch();
//            System.out.println("result: " + result.toString());

//			List<Tuple> result = queryFactory.select(member, team)
//							.from(member)
//							. leftJoin(member.team, team).on(team.name.eq("teamA")).fetch();
//
//			for(Tuple t : result){
//				System.out.println("tuple: " + t);
//			}


            // 서브쿼리
            // 나이가 가장 많은 회원 조회
            QMember msub = new QMember("membersub");
//            List<Member> result = queryFactory.selectFrom(member)
//            .where(member.age.eq(JPAExpressions.select(msub.age.max()).from(msub))).fetch();

//            System.out.println("result: " + result.get(0).getAge());

//            List<Member> result = queryFactory.selectFrom(member).where(member.age.goe(JPAExpressions.select(msub.age.avg()).from(msub))).fetch();

//            List<Member> result = queryFactory.selectFrom(member)
//                            .where(member.age.in(
//                                    JPAExpressions.select(msub.age)
//                                            .from(msub)
//                                            .where(msub.age.gt(10))
//                            ))
//                                    .fetch();

            // csae문
//            List<String> result = queryFactory.select(
//                    member.age.when(10).then("열열열살")
//                            .when(20).then("응애성인")
//                            .otherwise("-틀-")
//                    )
//                            .from(member)
//                                    .fetch();
//            System.out.println(result);

            // 복잡한 조건
//            List<String> result = queryFactory.select(new CaseBuilder()
//                            .when(member.age.between(0, 20)).then("0~20살")
//                            .when(member.age.between(21, 30)).then("21~30살")
//                            .otherwise("기타"))
//                            .from(member)
//                                    .fetch();
//            for(String s : result){
//                System.out.println("s: " + s);
//            }

            // 상수, 문자 더하기
//            List<Tuple> result = queryFactory.select(member.username, Expressions.constant("A"))
//                    .from(member).fetch();
//            for(Tuple t : result){
//                System.out.println("tuple: " + t);
//            }
//            /////////////////////////////////////
//            List<String> result2 = queryFactory
//                    .select(member.username.concat("_").concat(member.age.stringValue()))
//                            .from(member)
//                                    .where(member.username.eq("member1"))
//                                            .fetch();
//
//            for(String s : result2){
//                System.out.println(s);
//            }

            // 프로젝션 대상이 하나인 경우
//            List<String> result = queryFactory
//                    .select(member.username)
//                            .from(member)
//                                    .fetch();
//
//            for(String s : result){
//                System.out.println(s);
//            }
//
//            // 프로젝션 대상이 둘 이상일 때 -> 튜플
//            List<Tuple> result2 = queryFactory
//                    .select(member.username, member.age)
//                    .from(member)
//                    .fetch();
//
//            for(Tuple t : result2){
//                String username = t.get(member.username);
//                Integer age = t.get(member.age);
//                System.out.println("username: " + username + " age: " + age);
//            }

            // DTO 조회
            List<MemberDto> result = em.createQuery(
                    "select new com.codingbox.querydsl2.domain.MemberDto(m.username, m.age) " +
                            "from Member m", MemberDto.class
                    ).getResultList();
            for(MemberDto dto : result) {
                System.out.println(dto);
            }
            // ==========================================================
            /*
            프로퍼티 접근: Getter, Setter -> Bean
            1param: MemberDto.class -> type 지정
            2param: 꺼내올 값 나열
             */

            List<MemberDto> result2 = queryFactory
                    .select(Projections.bean(MemberDto.class,
                            member.username, member.age))
                            .from(member)
                                    .fetch();
            for(MemberDto d : result2){
                System.out.println(d);
            }

            List<MemberDto> result3 = queryFactory
                    .select(Projections.fields(MemberDto.class,
                            member.username.as("name"), member.age))
                            .from(member)
                                    .fetch();
            for(MemberDto d : result3){
                System.out.println(d);
            }


            List<MemberDto> result4 = queryFactory
                    .select(Projections.constructor(MemberDto.class,
                            member.username, member.age))
                            .from(member)
                                    .fetch();
            for(MemberDto d : result4){
                System.out.println(d);
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }


    }

}












