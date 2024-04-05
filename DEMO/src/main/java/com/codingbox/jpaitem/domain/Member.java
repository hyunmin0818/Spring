package com.codingbox.jpaitem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(name = "MEMBER_SEQ+GENEATOR",
                    sequenceName = "MEMBER_SEQ",
                    initialValue = 1, allocationSize = 1)
public class Member {
    @Id
    @GeneratedValue() // AUTO
    @Column(unique = true, name = "USER_ID")
    private long id;

    private String name;

    private String city;

    private String street;

    private String zipcode;
}
