package com.codingbox.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private long id;
    private String name;
    private int age;

    @Embedded
    private Address address;

    // order table에 있는 member 테이블에 의해서 수정된다
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
