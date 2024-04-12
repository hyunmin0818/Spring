package com.codingbox.shop.controller;

import com.codingbox.shop.domain.Address;
import com.codingbox.shop.domain.Member;
import com.codingbox.shop.dto.MemberForm;
import com.codingbox.shop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        // validation 처리와 같은 내용 때문에 빈값이라도 보내준다.
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    // @Valid 다음에 BindingResult가 있으면, error가 발생시 Binding에 담아준다.
    @PostMapping("/members/new")
    public String createMember(@Valid MemberForm memberForm, BindingResult result) {
        // 에러가 발생하면 원래 페이지로 돌아가도록 설정
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getStreet(), memberForm.getCity(), memberForm.getZipcode());
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";

    }
    // url: /members
    // model: select한 결과값 담아주기
    // return: members/memberList
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        // 모델에 회원 목록 추가
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
