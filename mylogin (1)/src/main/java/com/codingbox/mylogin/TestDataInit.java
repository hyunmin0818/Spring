package com.codingbox.mylogin;

import com.codingbox.mylogin.domain.item.Item;
import com.codingbox.mylogin.domain.item.ItemRepository;
import com.codingbox.mylogin.domain.member.Member;
import com.codingbox.mylogin.domain.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // init() 안에, itemA, 10000, 10/ itemB, 20000, 20
    // 테스트 데이터 추가
    @PostConstruct
    public void init() {
        memberRepository.save(new Member("test","test","tester" ));
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
    }
}
