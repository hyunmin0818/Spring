package com.codingbox.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(name = "MEMBER3_SEQ_GENERATOR",
                    sequenceName = "MEMBER3_SEQ", // db에 생성괴는 seq이름
                    initialValue = 1, allocationSize = 1)
public class Member3 {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "MEMBER3_SEQ_GENERATOR")
    private int Id;

    @Column(unique = true, name = "name")
    private String username;

    @Transient
    private int temp;
}
