package org.hugme.aiWeather.videoCall.model.service;

import org.hugme.aiWeather.videoCall.model.dao.VideoCallDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class VideoCallServiceImpl implements VideoCallService{
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private VideoCallDao dao;
	
	
}
