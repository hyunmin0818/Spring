package com.codingbox.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.codingbox.shop.domain.Address;
import com.codingbox.shop.domain.Member;
import com.codingbox.shop.dto.MemberForm;
import com.codingbox.shop.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/members/new")
	public String createForm(Model model) {
		// validation 처리와 같은 내용 때문에 빈값이라도 보내준다.
		model.addAttribute("memberForm", new MemberForm());
		return "members/createMemberForm";
	}
	
	// @Vaild다음에 BindingResult 있으면, error가 발생시 Binding에 담아준다.
	@PostMapping("/members/new")
	public String postMethodName(@Valid MemberForm form, 
									BindingResult result) {
		if( result.hasErrors() ) {
			return "members/createMemberForm";
		}
		
		Address address 
		= new Address(form.getCity(), form.getStreet(), form.getZipcode());
		Member member = new Member();
		member.setName(form.getName());
		member.setAddress(address);	
		
		memberService.join(member);				
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
	
	
	
	
}












