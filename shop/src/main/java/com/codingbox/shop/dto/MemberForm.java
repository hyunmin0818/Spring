package com.codingbox.shop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
	// 필수값에 대한 처리를 java에서 처리
	
	@NotEmpty(message = "회원 이름은 필수값입니다.")
	private String name;
	
	private String city;
	private String street;
	private String zipcode;
}
