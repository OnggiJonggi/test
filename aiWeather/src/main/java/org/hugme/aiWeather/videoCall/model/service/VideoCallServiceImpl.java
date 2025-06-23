package org.hugme.aiWeather.videoCall.model.service;

import javax.servlet.http.HttpSession;

import org.hugme.aiWeather.common.Regexp;
import org.hugme.aiWeather.member.model.vo.Member;
import org.hugme.aiWeather.videoCall.model.dao.VideoCallDao;
import org.hugme.aiWeather.videoCall.model.vo.VideoCall;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class VideoCallServiceImpl implements VideoCallService{
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private VideoCallDao dao;
	
	@Override
	public int createRoom(HttpSession session, VideoCall vc) {
		
		//최대 참여자 유효성 확인
		String maxParticipants = String.valueOf(vc.getMaxParticipants());
		if(!maxParticipants.matches(Regexp.MAXPARTICIPANTS)) return 0;
		
		Member m = (Member)session.getAttribute("loginUser");
		vc.setUserNo(m.getUserNo());
		vc.setVcId(Regexp.createUUID());
		
		int result = dao.createRoom(sqlSession,vc);
		
		return 0;
	}
}
