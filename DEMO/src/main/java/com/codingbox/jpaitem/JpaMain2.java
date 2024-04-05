package com.codingbox.jpaitem;

import com.codingbox.jpaitem.relration.Member;
import com.codingbox.jpaitem.relration.Team;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain2 {

    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = null;
        Member findMember = null;
        try {
            Team team = null;
            member = null;
            for (int i = 0; i < 10; i++) {
                team = new Team();
                team.setName("TeamA" + i);
                em.persist(team);

                member = new Member();
                member.setName("member1" + i);
                member.setTeam(team);
                em.persist(member);
            }

//         // 저장
//         Team team = new Team();
//         team.setName("TeamA");
//         em.persist(team);
//
//         Member member = new Member();
//         member.setName("member1");
//         member.setTeam(team);
//         em.persist(member);
//
            // 조회
            findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeamMember : " + findTeam.getName());

            // 수정
            Team newTeam2 = em.find(Team.class, 1L);
            findMember.setTeam(newTeam2);

            Team newTeam = em.find(Team.class, 5L);
            findMember.setTeam(newTeam);
            System.out.println("findTeamName: " + newTeam.getName());
            System.out.println("updateTeam.getId: " + newTeam.getId());

            // 강제로 db쿼리를 보고 싶을때
            em.flush();
            em.clear();

            tx.commit();
        } catch (Exception e) {
            Member findSideMember = em.find(Member.class, 5L);
            List<Member> members = findMember.getTeam().getMember();
            for (Member m : members) {
                System.out.println("result = " + m.getName());
            }

            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

}
