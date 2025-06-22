package org.hugme.aiWeather.videoCall.model.controller;

import org.hugme.aiWeather.videoCall.model.service.VideoCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VideoCallController {
	@Autowired
	private VideoCallService service;
	
	@GetMapping("videoCall/main")
	public String goVideoCallMain() {
		return "videoCall/vcMain";
	}
}
