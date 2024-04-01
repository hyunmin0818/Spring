package com.codingbox.mylogin.domain.member;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {
    private static Map<Long, Member> stroe = new HashMap<>();
    private static Long seq = 0L;

    public Member save(Member member) {
        member.setId(++seq);
        stroe.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return stroe.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(stroe.values());
    }

    public Member findByLoginId(String loginId) {
        List<Member> all = findAll();
        for(Member m : all) {
            if(m.getLoginId().equals(loginId)) {
                return m;
            }
        }
        // 조건 문에 안걸리 수 도 있기 때문에 null 값 반환을 추가
        return null;
    }
}
