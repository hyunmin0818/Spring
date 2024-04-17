package com.codingbox.querydsl2.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberDto implements Serializable {
    private String name;
    private int age;
    public MemberDto() {}

    public MemberDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}