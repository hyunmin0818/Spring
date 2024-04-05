package com.codingbox.jpaitem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

//@Entity
@Getter@Setter
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue()
    @Column(name = "ORDER_ID")
    private long id;
    @Column(name = "MEMBER_ID")
    private long memberId;
    private LocalDateTime orderDate;
    private String status;
}
