package org.hugme.aiWeather.videoCall.model.controller;

import javax.servlet.http.HttpSession;

import org.hugme.aiWeather.videoCall.model.service.VideoCallService;
import org.hugme.aiWeather.videoCall.model.vo.VideoCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VideoCallController {
	@Autowired
	private VideoCallService service;
	
	//영상통화 메인
	@GetMapping("videoCall/main")
	public String goVideoCallMain() {
		return "videoCall/vcMain";
	}
	
	//새로운 방 생성
	@PostMapping("videoCall/createRoom")
	public String createRoom(HttpSession session, Model model, VideoCall vc) {
		try {
			int result = service.createRoom(session,vc);
			if(result>0) {
				session.setAttribute("videoCallRoom", vc);
				return "videoCall/room";
			}else {
				session.setAttribute("alertMsg", "500 err");
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("alertMsg", "500 err");
			return "common/errorPage";
		} 
	}
	
	//기존 방 불러오기
	@GetMapping("videoCall/recallRoom")
	public String recallRoom(HttpSession session) {
		return null;
	}
	
	//방 참여하기
	@PostMapping("videoCall/participate/${sessionId}")
	public void createToken(@PathVariable String sessionId
			,HttpSession session) {
		
		
	}

}
