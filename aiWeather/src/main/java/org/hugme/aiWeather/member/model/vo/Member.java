package org.hugme.aiWeather.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
	private String userNo;
	private String userId;
	private String userPwd;
	private String roleType;
}
