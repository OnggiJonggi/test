package org.hugme.aiWeather.member.controller;

import javax.servlet.http.HttpSession;

import org.hugme.aiWeather.common.template.TokenGenerator;
import org.hugme.aiWeather.member.model.service.MemberService;
import org.hugme.aiWeather.member.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
	@Autowired
	public MemberService service;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	//회원가입 페이지 이동
	@GetMapping("insert.me")
	public String insertMember() {
		return "member/MemberEnrollForm";
	}
	
	//회원가입
	@PostMapping("insert.me")
	public String insertMember(HttpSession session, Model model
			, Member m, String domain) {
		try {
			System.out.println("도메인 첨가 및 암호화 전 : "+m);
			System.out.println("도메인 : "+domain);
			
			//---------유효성 검사---------//
			if(!m.getUserId().matches("^[a-zA-Z0-9]{1,30}$") ||
					!m.getUserPwd().matches("^[a-zA-Z0-9]{4,30}$") ||
					!m.getUserName().isEmpty()
					) {
				return errorPage(model, "필수 항목을 확인해 주세요.");
			}
			//입력받은 아이디와 도메인을 이메일 형식으로 변경
			m.setUserId(m.getUserId()+"@"+domain);
			
			//암호화
			m.setUserPwd(bcrypt.encode(m.getUserPwd()));
			
			System.out.println("최종 : "+m);
			//서비스로 보내
			int result = service.insertMember(m);
			
			if(result>0) {
				//토큰 생성기로 보내기
				result = new TokenGenerator().insertMemberToken(m);
				if(result>0) {
					session.setAttribute("alertMsg", "이메일을 보냈습니다.");
					return "redirect:/";
				}else {
					return errorPage(model,"이메일 송신이 실패하였습니다.");
				}
				
			}else {
				return errorPage(model,"접근이 거부되었습니다.");
			}
			
		}catch(NullPointerException e) {
			e.printStackTrace();
			return errorPage(model,"작성하신 항목을 확인해 주세요.");
		}catch(Exception e) {
			e.printStackTrace();
			return errorPage(model,"회원가입에 실패하였습니다.");
		}
	}
	
	//아이디 중복확인
	@ResponseBody
	@RequestMapping("checkUserId.me")
	public String checkUserId(Model model, String userId, String domain) {
		try {
			//온전한 이메일 형식으로 바꿔
			String id;
			switch(domain) {
			case "naver" :
			case "goole" : 
				id = userId+"@"+domain+".com";
				break;
			default : 
				id = userId+"@"+domain;
			}
			
			int result = service.checkUserId(id);
			
			if(result>0) {
				return "noPass";
			}else {
				return "pass";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return "err";
		}
	}
	
	//유저 식별번호로 유저 아이디 조회
	public String selectUserId(int userNo) {
		try {
			String userId = service.selectUserId(userNo);
			return userId;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	//오류창
	private String errorPage(Model model, String errMsg) {
		model.addAttribute("errorMsg",errMsg);
		return "common/errorPage";
	}
}
