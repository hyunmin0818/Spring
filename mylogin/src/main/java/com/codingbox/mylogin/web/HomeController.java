package com.codingbox.mylogin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.codingbox.mylogin.domain.member.Member;
import com.codingbox.mylogin.domain.member.MemberRespository;
import com.codingbox.mylogin.session.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final MemberRespository memberRespository;

//	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	// 로그인 처리까지 되는 home화면을 확인
	// required = false 로그인 안한 사용자도 들어와야 한다.
//	@GetMapping("/")
	public String homeLoginv2(
		@CookieValue(name="memberId", required = false)Long memberId,
		Model model) {
		
		// 로그인한 사용자가 아니라면 home 화면으로 보낸다.
		if( memberId == null ) {
			return "home";
		}
		
		// db조회를 한후, 사용자가 없으면 다시 home 처리
		Member loginMember = memberRespository.findById(memberId);
		if(loginMember == null) {
			return "home";
		}
		
		// loginHome : 로그인에 성공한 사람만이 볼 수 있는 화면
		model.addAttribute("member", loginMember);
		return "loginHome";
	}
	
//	@GetMapping("/")
	public String homeLoginv3(HttpServletRequest request,Model model) {
		HttpSession session = request.getSession(false);
		if( session == null ) {
			return "home";
		}
		
		Member loginMember
			= (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
		// 세션에 회원 데이터가 없으면 home
		if( loginMember == null ){
			return "home";
		}
		
		// 세션이 유지되면 로그인으로 이동	
		model.addAttribute("member", loginMember);
		return "loginHome";
	}
	
	@GetMapping("/")
	public String homeLoginv4(
			// session에서 attribute로 뒤져서 loginMemeber값을 획득해서
			// loginMember라는 변수에 넣어준다.
			@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)Member loginMember,
			Model model
			) {
		
		// 세션에 회원 데이터가 없으면 home
		if(loginMember == null) {
			return "home";
		}
		
		// 세션이 유지되면 로그인으로 이동	
		model.addAttribute("member", loginMember);
		return "loginHome";
	}
	
	
	
	
	
}







