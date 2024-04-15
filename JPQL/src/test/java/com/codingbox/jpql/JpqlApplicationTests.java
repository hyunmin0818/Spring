package com.codingbox.jpql;

import com.codingbox.jpql.domain.Member;
import com.codingbox.jpql.domain.Team;
import com.codingbox.jpql.domain.repository.MemberRepository;
import com.codingbox.jpql.domain.repository.OrderRepository;
import com.codingbox.jpql.domain.repository.TeamRepository;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.util.List;

@SpringBootTest
class JpqlApplicationTests {
    @Autowired
    private MemberRepository memberRepository;
    private TeamRepository teamRepository;
    private OrderRepository orderRepository;
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;\

    @Test

    void contextLoads() {
        // 멤버 생성 및 저장
//        Member member = new Member();
//        member.setUsername("member1");
//        member.setAge(10);
//        memberRepository.save(member);
//
//        // username으로 멤버 조회
//        memberRepository.findByUsername("member1")
//                .ifPresent(m -> System.out.println(m.getUsername()));
//
//        // 모든 멤버의 나이를 20으로 변경
//        memberRepository.findAll().forEach(m -> m.setAge(20));
//
//        // 모든 Member의 Team 조회
//        List<Team> teams = memberRepository.findAllTeams();
//        teams.forEach(t -> System.out.println(t.getName()));
//
//        for(int i=0; i<100; i++){
//            Member member = new Member();
//            member.setUsername("member"+i);
//            member.setAge(i);
//
//        }
        @PersistenceContext
        public List<Member> findMembersOlderThanAverageAge() {
            String jpql = "select m from Member m where m.age > (select avg(m2.age) from Member m2)";
            return entityManager.createQuery(jpql, Member.class).getResultList();
        }
        Team team = new Team();
        team.setName("teamA");

    }
}
