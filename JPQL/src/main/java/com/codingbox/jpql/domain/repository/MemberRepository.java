package com.codingbox.jpql.domain.repository;

import com.codingbox.jpql.domain.Member;
import com.codingbox.jpql.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m.username from Member m")
    List<Member> selectUsername();
    @Query("select m.username, m.age from Member m")
    Member selectUsernameAge();

    @Query("select m from Member m where m.username = ?1")
    Optional<Member> findByUsername(String username);

    // 모든 Member의 Team을 조회하는 쿼리
    @Query("select m.team from Member m")
    List<Team> findAllTeams();
}