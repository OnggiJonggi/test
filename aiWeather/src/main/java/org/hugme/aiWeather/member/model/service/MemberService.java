package org.hugme.aiWeather.member.model.service;

import org.hugme.aiWeather.member.model.vo.Member;

public interface MemberService {

	//회원가입
	int enroll(Member m);
	
	//비동기 - 아이디 체크
	String checkUserId(String userId);

	//로그인
	Member login(Member m);

	
}
