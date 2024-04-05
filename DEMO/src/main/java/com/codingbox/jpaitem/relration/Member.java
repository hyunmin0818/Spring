package com.codingbox.jpaitem.relration;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
//@Setter(value = AccessLevel.NONE)
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private long id;
    @Column(name = "USERNAME")
    private String name;
//    @Column(name = "TEAM_ID")
//    private long teamId;

//    @ManyToOne: 여기서는 team하나
//    @JoinColumn: 관계 컬럼을 적어준다. TEAM_ID와 조인해야 한다.
//    외래키가 있는 객체가 주인이다.
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void changeTeam(Team team) {
        this.team = team;
        // this: 나 자신의 인스턴스를 넣어준다.
        team.getMember().add(this);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
        // 무한루프 조심
//    @Override
//    public String toString() {
//        return "Member{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", team=" + team +
//                '}';
//    }
}
