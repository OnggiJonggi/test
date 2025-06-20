package org.hugme.aiWeather.common;

import java.net.InetAddress;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FindIpAddress {
	
	
	//서버 내부ip주소 확인
	@ResponseBody
	@PostMapping("findServerPrivateIP")
	public String findServerPrivateIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
