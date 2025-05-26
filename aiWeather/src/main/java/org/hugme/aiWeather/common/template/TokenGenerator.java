package org.hugme.aiWeather.common.template;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import org.hugme.aiWeather.email.controller.EmailController;
import org.hugme.aiWeather.member.vo.Member;

//토큰 생성 메소드
public class TokenGenerator {
	
    // 허용할 문자 :  특수문자 + 대소문자 + 숫자
    final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    final String NUMBER = "0123456789";
    final String SPECIAL = "!@#$%^&*()_+-=[]{}|;:,.<>?";
    final String ALL_CHARS = CHAR_LOWER + CHAR_UPPER + NUMBER + SPECIAL;
    
    // 암호학적으로 안전한 난수 생성기
    final SecureRandom random = new SecureRandom();
    
    // 토큰 문자열 수
    final int TOKENLENGTH = 32;
    /*
     * 32글자 기준으로 base64인코딩하면 44글자 -> 패딩 없음
     * 글자수 바꾸고 디코딩하려면 패딩 넣어줘야함
     * 
     * base64로 인코딩된 문자열을 그대로 db에 집어넣을거라
     * 디코딩 신경 안 써도 되는데
     * 토큰 문자열 크기 바꾸면 db에서 토큰 문자열 크기 제한 조심
     */

    //토큰 생성
    public String generateToken() {
        StringBuilder token = new StringBuilder(TOKENLENGTH);
        for (int i = 0; i < TOKENLENGTH; i++) {
            int randomIndex = random.nextInt(ALL_CHARS.length());
            token.append(ALL_CHARS.charAt(randomIndex));
        }
        
        //base64인코딩 전에 byte배열로 바꿔주기
        byte[] tokenBytes = token.toString().getBytes(StandardCharsets.UTF_8);

        //패딩 제거 URL-safe base64인코딩
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
    
    
    //회원가입 - 토큰 생성 및 이메일 컨트롤러로 보내기
    public int insertMemberToken(Member m) {
    	String token = generateToken();
    	return new EmailController().insertMemberEmail(m,token);
    }
}
