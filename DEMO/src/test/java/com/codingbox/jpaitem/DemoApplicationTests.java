package com.codingbox.jpaitem;

import com.codingbox.jpaitem.relration.Member;
import com.codingbox.jpaitem.relration.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Team team = new Team();
        team.setName("Team22222");
        em.persist(team);

        Member member = new Member();
        member.setName("member2222222");
        member.setTeam(team);
        em.persist(member);

        em.flush();
        em.clear();

        System.out.println("=================================");
        Team findTeam = em.find(Team.class, team.getId());
        List<Member> members = findTeam.getMember();

        for(Member m : members){
            System.out.println("m = " + m.getName());
        }
    }

}
