package org.hugme.aiWeather.email.model.service;

import org.hugme.aiWeather.email.vo.Email;
import org.hugme.aiWeather.email.vo.MemberToken;

public interface EmailService {

	//단순 이메일 보내기
	void sendEmail(Email email);

	//회원가입 - 생성된 토큰 저장
	int insertMemberToken(MemberToken memberToken);

	//회원가입 - 이메일 송출
	int insertMemberSendEmail(String userId, MemberToken memberToken);

}
