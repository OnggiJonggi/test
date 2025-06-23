package org.hugme.aiWeather.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.openvidu.java.client.OpenVidu;

@Component
public class OpenViduConfig {
    @Value("${openvidu.url}")
    private String URL;
    
    @Value("${openvidu.urllh}")
    private String URLLH;
    
    @Value("${openvidu.secret}")
    private String SECRET;
	
    @Bean
    public OpenVidu openViduBean() {
        return new OpenVidu(URL, SECRET);
    }
}