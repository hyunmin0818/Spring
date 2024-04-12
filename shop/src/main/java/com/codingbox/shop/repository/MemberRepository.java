package com.codingbox.shop.repository;

import com.codingbox.shop.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // jpa가 지원해주는 표준
    // sping이 entitymanager를 만들어서 em에다가 주입
//    @PersistenceContext
    /*
    * @PersistenceContext가 있어야 표준 EntityManager주입이 가능하다.
    * 그러나, spring의 @Autowired가 주입이 되도록 이러한 지원을 해준다.
    */
    @Autowired
    private static EntityManager em;

    // 저장
    public void save(Member member) {
        em.persist(member);
    }
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    // 특정 이름으로 전체 검색
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class) .setParameter("name", name).getResultList();
    }

    // 한명만 검색
    public static Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }

}
