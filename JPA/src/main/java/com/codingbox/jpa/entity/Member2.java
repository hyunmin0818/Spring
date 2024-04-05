package com.codingbox.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

//@Entity
@Getter @Setter
//@Table(name = "MBR")
@NoArgsConstructor // 생성자를 알아서 만들어줌
public class Member2 {
    @Id
    private long id;
    @Column(unique = true, length = 10)
    private String name;

    // 컬럼명 지정
    @Column(name = "myage")
    private int age;

    /*
     * Date: java.sql.Date - jpa : TIME, TIMESTAMP(X)
     * Date: java.util.Date - jpa : TIME, TIMESTAMP(O)
     */
    // 날짜 타입 매핑
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date createDate;

    // 날짜 타입 매핑
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModiredDate;

    // 매핑 무시
    @Transient
    private int temp;
}
