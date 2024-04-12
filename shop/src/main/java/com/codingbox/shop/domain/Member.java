package com.codingbox.shop.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
	@Id
	@GeneratedValue
	@Column(name="member_id")
	private Long id;
	private String name;
	
	// 주소
	@Embedded
	private Address address;
	
	// order table에 있는 member 테이블에 의해서 수정된다.
	@OneToMany(mappedBy= "member")
	private List<Order> orders = new ArrayList<>();
}
