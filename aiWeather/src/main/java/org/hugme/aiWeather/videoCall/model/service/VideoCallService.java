package org.hugme.aiWeather.videoCall.model.service;

import javax.servlet.http.HttpSession;

import org.hugme.aiWeather.videoCall.model.vo.VideoCall;

public interface VideoCallService {

	//새로운 방 생성
	int createRoom(HttpSession session, VideoCall vc) throws Exception;

}
