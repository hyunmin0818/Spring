package com.codingbox.mylogin;

import org.springframework.stereotype.Component;

import com.codingbox.mylogin.domain.item.Item;
import com.codingbox.mylogin.domain.item.ItemRepository;
import com.codingbox.mylogin.domain.member.Member;
import com.codingbox.mylogin.domain.member.MemberRespository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDataInit {
	
	private final MemberRespository memberRespository;
	private final ItemRepository itemRepository;
	
	// 테스트 데이터 추가
	@PostConstruct
	public void init() {
		Member member = new Member();
		member.setLoginId("test");
		member.setPassword("test");
		member.setName("테스트");
		memberRespository.save(member);
		itemRepository.save(new Item("itemA", 10000, 10));
		itemRepository.save(new Item("itemB", 20000, 20));
	}
}













