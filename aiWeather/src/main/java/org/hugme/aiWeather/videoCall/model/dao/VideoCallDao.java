package org.hugme.aiWeather.videoCall.model.dao;

import java.util.List;

import org.hugme.aiWeather.member.model.vo.Member;
import org.hugme.aiWeather.videoCall.model.vo.VcMember;
import org.hugme.aiWeather.videoCall.model.vo.VideoCall;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VideoCallDao {

	public int createRoom(SqlSessionTemplate sqlSession, VideoCall vc) {
		return sqlSession.insert("videoCallMapper.createRoom",vc);
	}
	
	public int countMyVcRoom(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("videoCallMapper.countMyVcRoomList",m);
	}
	
	public List<VideoCall> myRoomList(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectList("videoCallMapper.myRoomList",m);
	}

	public int insertParticipate(SqlSessionTemplate sqlSession, VcMember vcm) {
		return sqlSession.insert("videoCallMapper.insertParticipate",vcm);
	}

	public String haveLicense(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("videoCallMapper.haveLicense",m);
	}

}
