package com.codingbox.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.shop.domain.Member;
import com.codingbox.shop.repository.MemberRepository;


import lombok.RequiredArgsConstructor;

/*
 * @Transactional
 *  - DB와 관련된, 트랜잭션이 필요한 서비스 클래스 혹은 메서드에 
 *  @Transactional 추가
 *  - 일련의 작업들을 묶어서 하나의 단위로 처리할때
 *  - spring이 제공해주는 것을 권장 
 *  - 옵션 : readOnly = true or false
 *  	-> 읽기 전용일때 사용
 *  	-> 비용을 아끼게 된다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;
	
	@Transactional
	public Long join(Member member) {
		memberRepository.save(member);
		
		return member.getId();
	}
	
	//@Transactional(readOnly = true)
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
}
