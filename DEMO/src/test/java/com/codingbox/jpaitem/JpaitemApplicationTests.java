package com.codingbox.jpaitem;

import com.codingbox.jpaitem.embedded.Address;
import com.codingbox.jpaitem.embedded.Member;
import com.codingbox.jpaitem.embedded.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@Rollback(false)
class JpaitemApplicationTests {
	@Autowired
	MemberRepository memberRepository;

	@Test
	void contextLoads() {
//      List<Member> list = memberRepository.findAll();
//      for (Member m : list) {
//         System.out.println("member = " + m.getTeam().getName());
//      }

//       양방향 매핑 시 가장 많이 하는 실수
//      Member member = new Member();
//      member.setName("member1111111");
//      memberRepository.save(member);
//      Team team = new Team();
//      team.setName("team111111111");
//      team.getMember().add(member);
//      teamRepository.save(team);

		// jpa main3
		// 수정
//      for (int i = 0; i<10; i++) {
//         Team team = new Team();
//         team.setName("team"+i);
//         teamRepository.save(team);
//         Member member = new Member();
//         member.setName("member"+i);
//         member.changeTeam(team);
//         memberRepository.save(member);
//      }


//      Optional<Team> findTeam = teamRepository.findById(1L);
//      List<Member> members = findTeam.get().getMember();
//      for(Member m : members) {
//         System.out.println("m = "+ m.toString() );
//      }
//
//   }
//		//04-08
//		Member member = new Member();
//		member.setUsername("user");
//		member.setAddress(new Address("seoul", "gangnam", "123"));
//		member.setPeriod(new Period(LocalDateTime.now(), LocalDateTime.now().plusDays(10)));
//		memberRepository.save(member);


		Address addr = new Address("서울", "강남", "123");
		Member member;
        member = new Member();
        member.setUsername("user1");
		member.setAddress(addr);
		memberRepository.save(member);
		// 새로운 객체를 생성한다.
		Address copyAddr = new Address(addr.getCity(), addr.getState(), addr.getZipcode());

		Member member2;
        member2 = new Member();
        member2.setUsername("user2");
		member2.setAddress(addr);
		memberRepository.save(member2);
	}
}


