package com.codingbox.test.repository;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter @Setter
@NoArgsConstructor
public class Student {

    private Long studentId;
    private String studentName;
    private int age;
    private int subject;
    private String phone;
    private String addr;

    public Student(Long studentId, String studentName, int age, int subject, String phone, String addr) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.subject = subject;
        this.phone = phone;
        this.addr = addr;
    }
}
