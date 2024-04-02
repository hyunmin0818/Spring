package com.codingbox.mylogin.domain.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final MemberRespository memberRespository;
	
	/*
	 * @ModelAttribute("member") Member member 
	 *  -> model.addAttribute("member", new Member()); 를 대신
	 */
	@GetMapping("/add")
	public String addForm(@ModelAttribute("member") Member member) {
		return "members/addMemberForm";
	}
	
	
}









