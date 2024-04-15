package com.codingbox.querydsl.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter
@NoArgsConstructor
//@ToString(of = {"id", "username", "age"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private int id;

    private String name;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    public Member(String name, int age, Team team) {
        super();
        this.name = name;
        this.age = age;
        if(team != null){
            changeTeam(team);
        }
    }

    public Member(String username, int age){
        this(username, age, null);
    }

    public Member(String username){
        this(username, 0);
    }

    @Override
    public String toString() {
        return "Member [name=" + name + ", age=" + age + ", team=" + team + "]";
    }

}
