package com.codingbox.core.controller;

import com.codingbox.core.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

    @RequestMapping("member")
    public String getMember(Model model) {
        MemberDTO member = new MemberDTO(1, "자바학생", "010-1111-2222");
        model.addAttribute("member", member);
        return "thymeleaf/member";
    }
}
