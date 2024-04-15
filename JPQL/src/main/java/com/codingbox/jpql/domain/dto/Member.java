package com.codingbox.jpql.domain.dto;

import com.codingbox.jpql.domain.Team;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Member {
    private String username;
    private int age;

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
