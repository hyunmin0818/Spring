package com.codingbox.jpaitem.relration.repository;

import com.codingbox.jpaitem.relration.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
