package org.hugme.aiWeather.member.model.service;

import org.hugme.aiWeather.member.vo.Member;

public interface MemberService {

	//회원가입
	int insertMember(Member m);

	//아이디 중복확인
	int checkUserId(String id);

	//유저 식별번호로 아이디 검색
	String selectUserId(int userNo);
	
}
