package org.hugme.aiWeather.videoCall.model.service;

import javax.servlet.http.HttpSession;

import org.hugme.aiWeather.common.Regexp;
import org.hugme.aiWeather.member.model.vo.Member;
import org.hugme.aiWeather.videoCall.model.dao.VideoCallDao;
import org.hugme.aiWeather.videoCall.model.vo.VideoCall;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;



@Service
public class VideoCallServiceImpl implements VideoCallService{
	@Autowired
	private SqlSessionTemplate sqlSession;
    @Autowired
    private OpenVidu openvidu;
	@Autowired
	private VideoCallDao dao;
	
	//새로운 방 생성
	@Override
	@Transactional(rollbackFor = {OpenViduJavaClientException.class, OpenViduHttpException.class})
	public int createRoom(HttpSession session, VideoCall vc) throws Exception{
		
		//유효성 확인
		String maxParticipants = String.valueOf(vc.getMaxParticipants());
		String vcName = vc.getVcName();
		if(!maxParticipants.matches(Regexp.MAXPARTICIPANTS)
				|| !vcName.matches(Regexp.VCNAME)) return 0;
		
		Member m = (Member)session.getAttribute("loginUser");
		vc.setUserNo(m.getUserNo());
		vc.setVcId(Regexp.createUUID());
		
		//db에 저장하지 않는, 프론트 조회용 데이터
		vc.setUserName(m.getUserName());
		vc.setNameSeed(m.getNameSeed());
		
		//openvidu세션 생성
		SessionProperties properties = new SessionProperties.Builder().build();
		Session vcSession = openvidu.createSession(properties);
		String sessionId = vcSession.getSessionId();
		vc.setVcSession(sessionId);
		
		return dao.createRoom(sqlSession,vc);
	}
}
