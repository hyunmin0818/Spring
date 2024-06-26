package com.codingbox.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter @Setter
//@Table(name = "MBR")
//NoArgsConstructor // 생성자를 알아서 만들어줌
public class Member {
    @Id
    private long id;
    private String name;
    private int age;

}
