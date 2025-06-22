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
	public String enroll(HttpSession session) { 
		return null;
	}
	
	//로그인
	@ResponseBody
	@PostMapping("member/login")
	public String login(HttpSession session, Member m){
		try {
			session.setAttribute("loginUser", m);
			return "pass";
		} catch (Exception e) {
			return "noPass";
		}
	}
}
