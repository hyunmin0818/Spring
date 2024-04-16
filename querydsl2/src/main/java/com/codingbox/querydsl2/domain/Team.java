package com.codingbox.querydsl2.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Team {

	@Id @GeneratedValue
	@Column(name = "team_id")
	private Long id;
	private String name;
	
	@OneToMany(mappedBy = "team")
	private List<Member> members
		= new ArrayList<>();
	
	public Team(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Team [id=" + id 
				+ ", name=" + name + "]";
	}
	
	
	
}










