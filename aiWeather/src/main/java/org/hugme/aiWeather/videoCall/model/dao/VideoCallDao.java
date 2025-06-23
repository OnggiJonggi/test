package org.hugme.aiWeather.videoCall.model.dao;

import org.hugme.aiWeather.videoCall.model.vo.VideoCall;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VideoCallDao {

	public int createRoom(SqlSessionTemplate sqlSession, VideoCall vc) {
		return sqlSession.insert("videoCallMapper.createRoom",vc);
	}

}
