package org.hugme.aiWeather.member.model.controller;

import javax.servlet.http.HttpSession;

import org.hugme.aiWeather.member.model.service.MemberService;
import org.hugme.aiWeather.member.model.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	
	//회원가입 페이지로
	@GetMapping("member/enroll")
	public String goEnroll() {
		return ("member/enroll");
	}
	
	//회원가입
	@PostMapping("member/enroll")
	public String enroll(HttpSession session, Member m) { 
		try {
			int result = service.enroll(m);
			if(result>0) {
				return "redirect:/";
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("alertMsg", "회원가입 실패!");
			return null;
		}
	}
	
	//비동기 - 아이디 중복확인
	@ResponseBody
	@PostMapping("member/checkUserId")
	public String checkUserId(String userId) {
		try {
			return service.checkUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return "err";
		}
	}
	
	
	//로그인
	@ResponseBody
	@PostMapping("member/login")
	public String login(HttpSession session, Member m){
		try {
			m = service.login(m);
			
			if(m == null || m.getUserNo()==null) {
				return "noPass";
			}
			
			session.setAttribute("loginUser", m);
			return "pass";
		} catch (Exception e) {
			e.printStackTrace();
			return "noPass";
		}
	}
}
