package com.codingbox.jpaitem.relration;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity@Getter@Setter@NoArgsConstructor
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEM_ID")
    private long id;
    private String name;

//    team에 의해서 관리가 된다.
//    mappedBy가 적힌 곳은 읽기만 가능하다.
//    값을 넣어봐야 아무일도 벌어지지 않는다.
    @OneToMany(mappedBy = "team")
    private List<Member> member = new ArrayList<>();


//    @Override
//    public String toString() {
//        return "Team{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", member=" + member +
//                '}';
//    }
}