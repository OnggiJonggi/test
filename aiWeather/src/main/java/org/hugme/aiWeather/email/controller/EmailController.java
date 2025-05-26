package org.hugme.aiWeather.email.controller;

import javax.servlet.http.HttpSession;

import org.hugme.aiWeather.email.model.service.EmailService;
import org.hugme.aiWeather.email.vo.Email;
import org.hugme.aiWeather.email.vo.MemberToken;
import org.hugme.aiWeather.member.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmailController {
    @Autowired
    private EmailService service;

    //이메일 보내보기
    @RequestMapping(value="sendEmail", method=RequestMethod.POST)
    public String sendEmail(Email email, HttpSession session) {
    	
    	if(email==null) {
    		session.setAttribute("alertMsg", "제출한 서식에 접근할 수 없습니다.");
    		return "common/errorPage";
    	}else if(email.getToAddress()== null || email.getToAddress().isEmpty()) {
    		session.setAttribute("alertMsg", "이메일 주소를 확인해 주세요.");
    		return "redirect:/";
    	}
    	
    	try {
    		service.sendEmail(email);
    		session.setAttribute("alertMsg", "메일이 송신되었습니다");
    		return "redirect:/";
    	}catch(Exception e) {
    		e.printStackTrace();
    		session.setAttribute("alertMsg", "제출 실패.");
    		return "redirect:/";
    	}
    }
    
    //회원가입
    public int insertMemberEmail(Member m, String token) {
    	MemberToken mt = new MemberToken(m.getUserNo(),token);
    	String userId = m.getUserId();
    	
    	//토큰 데이터 저장
    	int result = service.insertMemberToken(mt);
    	
    	//데이터 저장 성공 및 추출 성공하면 메일 송신
    	if(result>0 && mt.getTokenNo()>0) {
    		return service.insertMemberSendEmail(userId,mt);
    	}else {
    		return 0;
    	}
    }
    
    //회원가입 - 이메일 링크 클릭할때
    @RequestMapping(value="confirmEmail", method=RequestMethod.GET)
    public String confirmEmail(int tokenNo, String token) {
    	
        
    	return null;
    }
}
