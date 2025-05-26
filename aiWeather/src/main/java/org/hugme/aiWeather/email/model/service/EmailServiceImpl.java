package org.hugme.aiWeather.email.model.service;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.hugme.aiWeather.email.model.dao.EmailDao;
import org.hugme.aiWeather.email.vo.Email;
import org.hugme.aiWeather.email.vo.MemberToken;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/*
 * bean에서 의존성 추가하고 싶으면
 * @Service, @Autowired 제거 
 */
@Service
public class EmailServiceImpl implements EmailService{
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private EmailDao dao;
	
	//이메일 제목/내용 저장소 연결
	private Properties prop = new Properties();
	public EmailServiceImpl() {
		try {
			ClassPathResource resource = new ClassPathResource("mappers/emailTemplate.xml");
			InputStream inputStream = resource.getInputStream();
			prop.loadFromXML(inputStream);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
    private JavaMailSender mailSender;
	
    //단순 이메일 보내기
    @Override
    public void sendEmail(Email email){
    	try {
	    	MimeMessage mimeMessage = mailSender.createMimeMessage();
	    	MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	    	
	        message.setTo(email.getToAddress());
	        message.setSubject(email.getSubject());
			message.setText(email.getContent(), true);
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    }


	@Override
	public int insertMemberToken(MemberToken memberToken) {
		return dao.insertMemberToken(sqlSession,memberToken);
	}
	

	@Override
	public int insertMemberSendEmail(String userId, MemberToken mt){
		try {
	    	MimeMessage mimeMessage = mailSender.createMimeMessage();
	    	MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	    	
	        message.setTo(userId);
	        message.setSubject(prop.getProperty("insertMeSubject"));

	        /*
	         *  이메일 내용 : 
	         *  <a href="http://localhost:(포트번호)8888/aiWeather/confirmEmail?
	         *  tokenNo=토큰번호&token=토큰" method="post">눌러서 회원가입 마무리</a>
	         */
	        String content = prop.getProperty("insertMeFirstContent")
	        		+mt.getTokenNo()+"&token="+mt.getToken()
	        		+prop.getProperty("insertMeLastContent");
	        
			message.setText(content, true);
			mailSender.send(mimeMessage);
			
			
			return 1; //정상 작동
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
